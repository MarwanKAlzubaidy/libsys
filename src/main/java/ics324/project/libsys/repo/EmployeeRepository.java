package ics324.project.libsys.repo;

import ics324.project.libsys.entities.user.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByuserName(String userName);
}