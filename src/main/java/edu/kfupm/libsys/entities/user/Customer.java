package edu.kfupm.libsys.entities.user;

import edu.kfupm.libsys.entities.BorrowRecord;
import edu.kfupm.libsys.entities.Fine;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Setter
@Getter
public class Customer extends User {

    private String fname;
    private String lname;

    @OneToMany(mappedBy = "customer")
    private Set<BorrowRecord> records;

    @OneToMany(mappedBy = "customer")
    private Set<Fine> fines;



    public Customer(String fname, String lname) {
        this.fname = fname;
        this.lname = lname;
        setRole("ROLE_USER");
    }

    public Customer() {
        setRole("ROLE_USER");
    }
}
