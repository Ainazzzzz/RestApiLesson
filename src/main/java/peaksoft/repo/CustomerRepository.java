package peaksoft.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.CustomerRequest;
import peaksoft.dto.CustomerResponse;
import peaksoft.model.Customer;

import java.util.List;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer, Long> {

    @Query("select new peaksoft.dto.CustomerResponse(c.id,c.email,c.age)  from Customer c")
    List<CustomerResponse> findAllCustomers();




}
