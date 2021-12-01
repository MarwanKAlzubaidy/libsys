package edu.kfupm.libsys.entities;

import edu.kfupm.libsys.entities.user.Customer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter


public class Fine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private int amount;
    private fine_status status;
    private String Reason;

    @JoinColumn(name = "customer_id", nullable = false)
    @ManyToOne(optional = false)
    private Customer customer;

}
