package edu.kfupm.libsys.repo;

import edu.kfupm.libsys.entities.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByuserName(String userName);
}