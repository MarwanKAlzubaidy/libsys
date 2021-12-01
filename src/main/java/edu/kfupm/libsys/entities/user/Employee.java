package edu.kfupm.libsys.entities.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Setter
@Getter
public class Employee extends User {

    private String fname;
    private String lname;

    public Employee(String fname, String lname) {
        this.fname = fname;
        this.lname = lname;
        setRole("ROLE_ADMIN");
    }

    public Employee() {
        setRole("ROLE_ADMIN");
    }
}
