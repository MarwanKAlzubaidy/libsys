package ics324.project.libsys.repo;

import ics324.project.libsys.entities.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByuserName(String userName);
}