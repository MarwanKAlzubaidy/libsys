package edu.kfupm.libsys.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
 @Setter
 @Getter
@MappedSuperclass
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false, unique = true)
    private String userName;
    @Column(nullable = false)
    private String password;
    @Email(message = "Email not valid")
    @Column(nullable = false, unique = true)
    private  String email;
    @Column(nullable = false)
    private String role;



}