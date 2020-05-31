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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "step_number")
    private Integer stepNumber;

    @Column(name = "description")
    private String description;

//    @ManyToOne (fetch = FetchType.LAZY)
//    @JoinColumn(name = "recipe_id")
//    private Recipe recipe;

    @Column(name = "recipe_id")
    private Long recipeId;

}
