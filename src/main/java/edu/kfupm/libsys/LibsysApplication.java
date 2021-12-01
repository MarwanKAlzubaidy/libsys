package edu.kfupm.libsys;

import edu.kfupm.libsys.entities.Author;
import edu.kfupm.libsys.entities.user.Employee;
import edu.kfupm.libsys.entities.user.EmployeeRepository;
import edu.kfupm.libsys.repo.AuthorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Date;
import java.util.HashSet;

@SpringBootApplication
public class LibsysApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibsysApplication.class, args);
    }

}
