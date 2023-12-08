package peaksoft.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import peaksoft.dto.*;
import peaksoft.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
@Tag(name = "Customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public PaginationResponse getAllCustomers(@RequestParam int page,@RequestParam int size){
        return customerService.getAllCustomers(page,size);
    }

    @PostMapping
    public UserDto save(@RequestBody @Valid CustomerRequest customer){
        return customerService.saveCustomer(customer);
    }

    @DeleteMapping("/{id}")
    public SimpleResponse delete(@PathVariable long id){
        return customerService.delete(id);

    }

    @PutMapping("/{id}")
    public void update(@PathVariable long id,@RequestBody @Valid CustomerRequest customer){
        customerService.update(id,customer);
    }

    @GetMapping("/get")
    public CustomerResponse getById(@RequestParam long id){
        return customerService.getById(id);
    }
}
