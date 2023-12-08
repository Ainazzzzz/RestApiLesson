package peaksoft.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.CustomerRequest;
import peaksoft.dto.CustomerResponse;
import peaksoft.model.Customer;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer, Long> {

    @Query("select new peaksoft.dto.CustomerResponse(c.id,c.email,c.age)  from Customer c")
    Page<CustomerResponse> findAllCustomers(Pageable pageable);



}
