package edu.kfupm.libsys.entities;


import edu.kfupm.libsys.entities.copy.Copy;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.sql.Date;
import java.util.Set;

@Entity
@Setter
@Getter


public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String edition;

    @Column(nullable = false, unique = true)
    private String ISBN;

    @Column(nullable = false)
    private Date publish_date;

    @PositiveOrZero(message = "cant be negative")
    private double price;
    private int shelf_num;
    private int copies_count;

    @ManyToMany
    @JoinTable(
            name="authors_write_books",
            joinColumns = @JoinColumn(name="book_id"),
            inverseJoinColumns = @JoinColumn(name="author_id"))
    private Set<Author> authors;

    @ManyToOne(optional = false)
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher   publisher;

    @OneToMany(mappedBy = "book")
    private Set<Copy> copies;



}
