package com.slgproduction.mealapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cooking_steps", schema = "mealdb")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CookingStep {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "step_number")
    private Integer stepNumber;

    @Column(name = "description")
    private String description;

    @Column(name = "recipe_id")
    private Integer recipeId;

}
