package edu.kfupm.libsys.entities;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;
import java.util.Set;
//complete attribute
@Entity
@Setter
@Getter
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotEmpty(message = "no empty first name")
    @Column(nullable = false)
    private String fname;
    private String lname;
    @Column(nullable = false)
    private Date b_date;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books;


}
