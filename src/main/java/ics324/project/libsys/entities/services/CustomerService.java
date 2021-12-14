package ics324.project.libsys.entities.services;


import ics324.project.libsys.entities.user.Customer;
import ics324.project.libsys.repo.EmployeeRepository;
import ics324.project.libsys.repo.CustomerRepository;

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
