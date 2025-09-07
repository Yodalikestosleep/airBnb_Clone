package com.example.yoda.airBnbApp.entity;

import com.example.yoda.airBnbApp.entity.enums.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne               //many guests can be associated with one user
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Integer age;

//    @ManyToMany //do not define jointable here again since its already defined in booking
//    //create another redundent join table which is not required
//    private Set<Booking> bookings;
}
