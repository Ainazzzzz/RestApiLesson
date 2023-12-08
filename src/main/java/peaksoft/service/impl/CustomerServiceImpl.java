package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.*;
import peaksoft.exceptions.AlreadyExistsException;
import peaksoft.exceptions.NotFoundException;
import peaksoft.model.Customer;
import peaksoft.model.User;
import peaksoft.repo.CustomerRepository;
import peaksoft.repo.UserRepo;
import peaksoft.service.CustomerService;
import peaksoft.service.UserService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final UserRepo userRepo;
    private final UserService userService;
    @Override
    public PaginationResponse getAllCustomers(int page, int size){
       Pageable pageable = PageRequest.of(page-1,size);
      Page<CustomerResponse> customerResponses= customerRepository.findAllCustomers(pageable);
      return PaginationResponse.builder()
              .customerResponseList(customerResponses.getContent())
              .totalPages(customerResponses.getTotalPages())
              .totalElements(customerResponses.getTotalElements())
              .build();
    }

    @Override
    public UserDto saveCustomer(CustomerRequest customer) {
        if(userRepo.existsByEmail(customer.getEmail())){
            throw new AlreadyExistsException("Customer with such email already exists");
        }
        User user = new User();
        user.setName(customer.getName());
        user.setSurname(customer.getSurname());
        user.setPassword(userService.generatePassword());
        userRepo.save(user);

        Customer customer1 = Customer.builder()
                .age(customer.getAge())
                .email(customer.getEmail())
                .user(user)
                .build();
      customerRepository.save(customer1);
      return new UserDto(
              customer1.getUser().getUsername(),
              customer1.getUser().getPassword()
      );
    }

    @Override
    public String update(long id, CustomerRequest customer) {
     Customer customer1 = customerRepository.findById(id).orElseThrow(
             ()-> new NotFoundException
                     (String.format("Customer with such and id %d doesnt exist",id)));
     customer1.setAge(customer.getAge());
     customer1.getUser().setName(customer.getName());
     customer1.getUser().setSurname(customer.getSurname());
     customer1.setEmail(customer.getEmail());
     customerRepository.save(customer1);
    return "customer with id" + customer1.getId() + " successfully updated";};

    @Override
    public CustomerResponse getById(long id) {
       Customer customer = customerRepository.findById(id).orElseThrow(
                ()-> new NotFoundException
                        (String.format("Customer with such and id %d doesnt exist",id)));
    return new CustomerResponse(
            customer.getId(),
            customer.getUser().getName(),
            customer.getUser().getSurname(),
            customer.getEmail(),
            customer.getAge(),
            customer.getUser().getPassword()
    );
    }

    @Override
    public SimpleResponse delete(long id) {
        if (!customerRepository.existsById(id)) {
            throw new NotFoundException(String.format("Customer with such and id %d doesnt exist", id));
        }
        customerRepository.deleteById(id);
        return new SimpleResponse("Customer with id " + id + " successfully deleted", HttpStatus.OK);
    }

}
