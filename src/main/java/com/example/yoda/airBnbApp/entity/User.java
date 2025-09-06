package com.example.yoda.airBnbApp.entity;

import com.example.yoda.airBnbApp.entity.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String name;

    @ElementCollection(fetch = FetchType.EAGER) //EAGER because we always want to load roles with user
    @Enumerated(EnumType.STRING) //This will store the enum as a string in the database
    private Set<Role> roles;



}
