package com.slgproduction.mealapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", schema = "mealdb")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
    private Set<DailyPlan> dailyPlans = new HashSet<>();

    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Basket> baskets = new HashSet<>();

}
