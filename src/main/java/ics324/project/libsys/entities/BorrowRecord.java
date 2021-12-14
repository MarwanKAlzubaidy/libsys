package ics324.project.libsys.entities;

import ics324.project.libsys.entities.copy.Copy;
import ics324.project.libsys.entities.user.Customer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
public class BorrowRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private Date   check_out_date;
    private Date   return_date;
    private Status status;


    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;


    @ManyToOne(optional = false)
    @JoinColumn(name = "copy_id", nullable = false)
    private Copy copy;

}
