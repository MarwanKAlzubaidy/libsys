package edu.kfupm.libsys.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    private String phone;

    @OneToMany(mappedBy = "publisher")
    private Set<Book> books;


}
