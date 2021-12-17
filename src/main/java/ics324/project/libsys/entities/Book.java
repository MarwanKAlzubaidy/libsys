package ics324.project.libsys.entities;


import ics324.project.libsys.entities.copy.Copy;
import ics324.project.libsys.entities.user.Customer;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;
import org.hibernate.validator.constraints.ISBN;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Setter
@Getter


public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;


    @NotEmpty
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String edition;
    @NotBlank
    @NotEmpty
    @ISBN
    @Column(nullable = false, unique = true)
    private String ISBN;

    @Past
    @Column(nullable = false)
    private LocalDate publishDate;

    @PositiveOrZero(message = "cant be negative")
    private double price;
    @Positive
    private int shelf_num;
    @Transient
    @Formula(
            "(select ((select count(*)from owned_copy o where id=o.book_id)+ (select count(*)from loaned_copy l where id=l.book_id)))"
    )
    private int copies_count;

    @Size(message = "At least one Author", min = 1)
    @NotNull
    @ManyToMany
    @JoinTable(
            name= "authors_write_books",
            joinColumns = @JoinColumn(name= "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name= "author_id", referencedColumnName = "id"))
    private Set<Author> authors = new java.util.LinkedHashSet<>();


    //reserved by cusomters

    @ManyToMany(mappedBy = "books")
    private Set<Customer> customers = new java.util.LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher   publisher;

    @OneToMany(mappedBy = "book")
    private Set<Copy> copies;

    @Override
    public String toString() {
        return title+" "+ISBN;
    }
}
