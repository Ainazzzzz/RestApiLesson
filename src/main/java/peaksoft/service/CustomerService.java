package peaksoft.service;

import peaksoft.dto.CustomerRequest;
import peaksoft.dto.CustomerResponse;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.UserDto;
import peaksoft.model.Customer;

import java.util.List;

public interface CustomerService {

    List<CustomerResponse> getAllCustomers();
    UserDto saveCustomer(CustomerRequest customer);

    String update(long id, CustomerRequest customer);

    CustomerResponse getById(long id);

    SimpleResponse delete(long id);


}
