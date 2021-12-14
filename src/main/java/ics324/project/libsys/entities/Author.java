package ics324.project.libsys.entities;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import java.time.LocalDate;
import java.util.Set;
//complete attribute
@Entity
@Setter
@Getter
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotEmpty(message = "no empty first name")
    @Column(nullable = false)
    private String firstName;
    private String LastName;

    @Past(message = "past date only")
    @NotNull(message = "please add date")
    @Column(nullable = false)
    private LocalDate b_date;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books;

    public Author(String fname, String lname, LocalDate b_date, Set<Book> books) {
        this.firstName = fname;
        this.LastName = lname;
        this.b_date = b_date;
        this.books = books;
    }

    @Override
    public String toString() {
        return firstName+" "+LastName;
    }
}
