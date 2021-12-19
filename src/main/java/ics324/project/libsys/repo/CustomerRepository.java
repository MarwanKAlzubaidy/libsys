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



    @Query(value="select * from customer c where c.id not  in(select b.customer_id from borrow_record b)",nativeQuery = true)
    List<Customer> findNewCustomer();
    @Query(value = "select * from customer c where c.id in (select  br.customer_id from borrow_record br where not br.status=0 and 119<(datediff(  current_date() , br.check_out_date  ))) and c.id in  (select  br.customer_id from borrow_record br where not br.status=0 having 2<(select count(customer_id)  from borrow_record bs where c.id=bs.customer_id and not  bs.status=0 ));",nativeQuery = true)
    List<Customer> getCust3onelate();
    @Query(value = "select * from customer c where c.id not IN (select f.customer_id from fine f); ",nativeQuery = true)

    List<Customer> getNoFine();
}