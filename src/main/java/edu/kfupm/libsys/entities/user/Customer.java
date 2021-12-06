package edu.kfupm.libsys.entities.user;

import edu.kfupm.libsys.entities.BorrowRecord;
import edu.kfupm.libsys.entities.Fine;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
public class Customer extends User {

    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "customer")
    private Set<BorrowRecord> records=new HashSet<>();

    @OneToMany(mappedBy = "customer")
    private Set<Fine> fines=new HashSet<>();



    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        setRole("ROLE_USER");
    }

    public Customer() {
        setRole("ROLE_USER");
    }
}
