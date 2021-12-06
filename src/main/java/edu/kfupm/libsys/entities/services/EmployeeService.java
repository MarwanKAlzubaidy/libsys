package edu.kfupm.libsys.entities.services;

import edu.kfupm.libsys.entities.user.Employee;
import edu.kfupm.libsys.repo.EmployeeRepository;
import edu.kfupm.libsys.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeService {
    final private EmployeeRepository repository;
    private final CustomerRepository customerRepositry;

    public Employee findByuserName(String userName) {
        return repository.findByuserName(userName);
    }

    public List<Employee> findAllEmployee() {
        return repository.findAll();
    }

    public void saveEmployee(Employee emp) throws Exception {
        if (customerRepositry.findByuserName(emp.getUserName()) == null)
            repository.save(emp);
        else throw new Exception();
    }

    public void delete(Employee employee) {

        repository.delete(employee);
    }
}
