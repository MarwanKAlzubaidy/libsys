package ics324.project.libsys.entities.user;


import ics324.project.libsys.repo.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    private Object Employee;

    @Bean
    CommandLineRunner commandLineRunner(EmployeeRepository repository) {
        return args -> {
            Employee admin = new Employee();
            admin.setPassword("admin");
            admin.setUserName("admin");
            admin.setLastName("admin");
            admin.setFirstName("admin");
            admin.setEmail("admin@admin");
            admin.setEnabled(true);

            repository.save(admin);
        };


    }
}
