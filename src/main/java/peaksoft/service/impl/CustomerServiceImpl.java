package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.CustomerRequest;
import peaksoft.dto.CustomerResponse;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.UserDto;
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
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserService userRepo;
    @Override
    public List<CustomerResponse> getAllCustomers()
    {
        return customerRepository.findAllCustomers();
    }

    @Override
    public UserDto saveCustomer(CustomerRequest customer) {
        User user = new User();
        user.setName(customer.getName());
        user.setSurname(customer.getSurname());
        user.setPassword(userRepo.generatePassword());
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
             ()-> new NoSuchElementException
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
                ()-> new NoSuchElementException
                        (String.format("Customer with such and id %d doesnt exist",id)));
    return new CustomerResponse(
//            customer.getId(),
//            customer.getUser().getName(),
//            customer.getUser().getSurname(),
//            customer.getEmail(),
//            customer.getAge(),
//            customer.getUser().getPassword(),
//            customer.getUser().getUsername()
    );
    }

    @Override
    public SimpleResponse delete(long id) {
        customerRepository.deleteById(id);
        return new SimpleResponse("Customer with id " + id + " successfully deleted", HttpStatus.OK);
    }
}
