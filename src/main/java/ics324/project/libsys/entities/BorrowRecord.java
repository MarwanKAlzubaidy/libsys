package ics324.project.libsys.entities;

import ics324.project.libsys.entities.copy.Copy;
import ics324.project.libsys.entities.user.Customer;
import ics324.project.libsys.enums.Status;
import lombok.Getter;
import lombok.Setter;

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
    @Future
    @NotNull
    @Column(nullable = false)
    private LocalDate   return_date;
    private Status status;


    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;


    @ManyToOne(optional = false)
    @JoinColumn(name = "copy_id", nullable = false)
    private Copy copy;


}
