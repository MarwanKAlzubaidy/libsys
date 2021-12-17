package ics324.project.libsys.repo;

import ics324.project.libsys.entities.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByuserName(String userName);
    @Query(value = "select * from customer c HAVING 5>(select count(*) from borrow_record b where c.id=b.customer_id and not b.status=0)",nativeQuery = true)
    List<Customer> availableForBorrow();


    @Query(value = "select * from customer c where c.id in (select r.customers_id from reserve r);",nativeQuery = true)
    List<Customer> customerHasReservation();
}