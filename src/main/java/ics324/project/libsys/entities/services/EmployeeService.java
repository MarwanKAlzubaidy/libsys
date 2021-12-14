package ics324.project.libsys.entities.services;

import ics324.project.libsys.entities.user.Employee;
import ics324.project.libsys.repo.EmployeeRepository;
import ics324.project.libsys.repo.CustomerRepository;
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
