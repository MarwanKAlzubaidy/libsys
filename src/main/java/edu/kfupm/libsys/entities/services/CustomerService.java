package edu.kfupm.libsys.entities.services;


import edu.kfupm.libsys.entities.user.Customer;
import edu.kfupm.libsys.repo.EmployeeRepository;
import edu.kfupm.libsys.repo.CustomerRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


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

    //public Customer findByuserName(String value) {
    //  }
}
