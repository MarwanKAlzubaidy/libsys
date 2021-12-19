
package ics324.project.libsys.entities;
import lombok.Setter;
import ics324.project.libsys.entities.copy.Copy;
import ics324.project.libsys.entities.user.Customer;
import ics324.project.libsys.enums.Status;
import lombok.Getter;


import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class BorrowRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(nullable = false)
    private LocalDate check_out_date;

    @NotNull
    @Column(nullable = false)
    private LocalDate returnDate;
    private Status status;
    private boolean extended;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;


    @ManyToOne(optional = false)
    @JoinColumn(name = "copy_id", nullable = false)
    private Copy copy;


    public String getName() {

        return id+" "+customer.getFullName()+" copyID: "+copy.getId();
    }

    @Override
    public String toString() {
        return getName();
    }
}
