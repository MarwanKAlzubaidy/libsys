package edu.kfupm.libsys.entities.copy;

import edu.kfupm.libsys.entities.Book;
import edu.kfupm.libsys.entities.BorrowRecord;
import edu.kfupm.libsys.entities.Return_status;
import edu.kfupm.libsys.entities.user.Availability;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Copy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @JoinColumn(name = "book_id", nullable = false)
    @ManyToOne(optional = false)
    private Book book;


    @OneToMany(mappedBy = "copy")
    private Set<BorrowRecord> records;

    private Availability availability;



}
