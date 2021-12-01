package edu.kfupm.libsys.entities;

import edu.kfupm.libsys.entities.copy.LoanedCopy;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String Location;

    @OneToMany(mappedBy = "library")
    private Set<LoanedCopy> copies ;



}
