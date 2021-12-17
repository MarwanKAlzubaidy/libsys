package ics324.project.libsys.entities.services;


import ics324.project.libsys.entities.user.Customer;
import ics324.project.libsys.repo.EmployeeRepository;
import ics324.project.libsys.repo.CustomerRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


@RequiredArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;

    public void saveCustomer(Customer customer) throws Exception {
        if (employeeRepository.findByuserName(customer.getUserName()) == null)
            customerRepository.save(customer);
        else
            throw new Exception();
    }

    public List<Customer> findAllCustomer() {
        return customerRepository.findAll();
    }

    public List<Customer> CustomersAvalibleForBorrow() {
        return customerRepository.availableForBorrow();
    }

    public void saveAll(Set<Customer> customer) {
        customerRepository.saveAll(customer);
    }

    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    public Customer findByUsername(String username) {
       return customerRepository.findByuserName(username);
    }
    //public Customer findByuserName(String value) {
    //  }
}
