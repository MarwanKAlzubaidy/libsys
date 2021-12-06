package edu.kfupm.libsys.repo;

import edu.kfupm.libsys.entities.user.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByuserName(String userName);
}