package ics324.project.libsys.entities.user;

import ics324.project.libsys.entities.Book;
import ics324.project.libsys.entities.BorrowRecord;
import ics324.project.libsys.entities.Fine;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
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


    @JoinTable(name = "reserve",
            joinColumns = @JoinColumn(name = "customers_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
    @ManyToMany()

    private Set<Book> books = new LinkedHashSet<>();

    public String getFullName(){

        return firstName+" "+lastName;
    }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        super.setEnabled(false);
        setRole("ROLE_USER");
    }





    public Customer() {
        setRole("ROLE_USER");
    }
}
