package ics324.project.libsys.entities.copy;

import ics324.project.libsys.entities.Book;
import ics324.project.libsys.entities.BorrowRecord;
import ics324.project.libsys.enums.Availability;
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
