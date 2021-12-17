package ics324.project.libsys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class LibsysApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibsysApplication.class, args);
    }

}
