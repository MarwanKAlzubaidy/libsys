package edu.kfupm.libsys.entities.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
 @Getter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotNull(message = "No empty")
    @NotEmpty(message = "no empty")
    @NotBlank(message = "No empty")
    @Column(nullable = false, unique = true)
    private String userName;
    @Column(nullable = false)
    private String password;
    @NotBlank
    @NotEmpty
    @NotNull
    @Email(message = "Email not valid")
    @Column(nullable = false, unique = true)
    private  String email;
    @Column(nullable = false)
    private String role;



}