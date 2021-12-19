package ics324.project.libsys.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Setter
@Getter


public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotEmpty(message = "not empty plz")
    @Column(nullable = false, unique = true)
    private String name;

    private String location;

    @Digits(integer = 12, fraction = 0)
    private String phone;

    @OneToMany(mappedBy = "publisher")
    private Set<Book> books;

    @Override
    public String toString() {
        return
                name;

    }
}
