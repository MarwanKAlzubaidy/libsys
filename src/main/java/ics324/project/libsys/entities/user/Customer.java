package ics324.project.libsys.entities.user;

import ics324.project.libsys.entities.Book;
import ics324.project.libsys.entities.BorrowRecord;
import ics324.project.libsys.entities.Fine;
import ics324.project.libsys.enums.fine_status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Iterator;
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
    @Transient

    private int totalNotPaid;

    @Transient

    private int totalPaid;

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

    public int getTotalNotPaid() {
        totalNotPaid=0;
      Iterator<Fine> fineIterator=fines.stream().iterator();
      while (fineIterator.hasNext())
      {
          Fine fine= fineIterator.next();
          if(fine.getStatus()== fine_status.NOT_PAID)
              totalNotPaid=totalNotPaid+fine.getAmount();

      }
       return  totalNotPaid;
    }

    public int getTotalPaid() {
        totalPaid=0;
        Iterator<Fine> fineIterator=fines.stream().iterator();
        while (fineIterator.hasNext())
        {
            Fine fine= fineIterator.next();
            if(fine.getStatus()== fine_status.PAID)
                totalPaid=totalPaid+fine.getAmount();

        }
        return totalPaid;
    }

    public Customer(){
    super.setEnabled(false);
    setRole("ROLE_USER");
    }
}
