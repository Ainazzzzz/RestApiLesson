package peaksoft.service;

import peaksoft.dto.*;
import peaksoft.model.Customer;

import java.util.List;

public interface CustomerService {

    PaginationResponse getAllCustomers(int page, int size);
    UserDto saveCustomer(CustomerRequest customer);

    String update(long id, CustomerRequest customer);

    CustomerResponse getById(long id);

    SimpleResponse delete(long id);

}
