package ics324.project.libsys.entities.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
public class Employee extends User {

    @NotEmpty
    @NotBlank
    @NotNull
    @Column(nullable = false)
    private String firstName;
    @NotNull
    @NotEmpty
    @NotBlank(message = "Not empty")
    private String lastName;

    public Employee(String fname, String lname) {
        this.firstName = fname;
        this.lastName = lname;
        setRole("ROLE_ADMIN");
    }

    public Employee() {
        setRole("ROLE_ADMIN");
    }
}
