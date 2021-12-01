package edu.kfupm.libsys.entities.copy;

import edu.kfupm.libsys.entities.Book;
import edu.kfupm.libsys.entities.BorrowRecord;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
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





}
