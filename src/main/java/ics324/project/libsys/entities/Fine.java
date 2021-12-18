package ics324.project.libsys.entities;

import ics324.project.libsys.entities.user.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter

@NoArgsConstructor
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

    public Fine(Customer customer, int v, String book_return_overDue,fine_status fineStatus) {
        this.customer=customer;
        this.amount=v;
        this.Reason=book_return_overDue;
        this.status=fineStatus;

    }
}
