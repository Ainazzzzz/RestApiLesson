package peaksoft.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.CustomerRequest;
import peaksoft.dto.CustomerResponse;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.UserDto;
import peaksoft.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class CustomerController {

    private final CustomerService customerService;


    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/all")
    public List<CustomerResponse> getAllCustomers(){
        return customerService.getAllCustomers();
    }


    @PostMapping("/create")
    public UserDto save(@RequestBody CustomerRequest customer){
        return customerService.saveCustomer(customer);
    }

    @DeleteMapping("/{id}")
    public SimpleResponse delete(@PathVariable  long id){
        return customerService.delete(id);

    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable long id,@RequestBody CustomerRequest customer){
        customerService.update(id,customer);
    }

    @GetMapping("/get")
    public CustomerResponse getById(@RequestParam long id){
        return customerService.getById(id);
    }
}
