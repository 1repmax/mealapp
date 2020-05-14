package com.slgproduction.mealapp.model;

import lombok.Data;

import javax.persistence.*;

@Entity
//@Table(name = "products", schema = "mealdb")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name")
    private String name;
}
