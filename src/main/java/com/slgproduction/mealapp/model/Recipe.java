package com.slgproduction.mealapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "recipes", schema = "mealdb")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "cooking_time")
    private Integer cookingTime;

    @Column(name = "usage_frequency")
    private Integer usageFrequency;

    @Column(name = "servings_per_recipe")
    private Integer servingsPerRecipe;

    @OneToMany (mappedBy = "recipe", cascade = CascadeType.ALL)
    private Set<Ingredient> ingredients = new HashSet<>();

    @OneToMany (mappedBy = "recipe", cascade = CascadeType.ALL)
    private Set<DailyPlan> dailyPlans = new HashSet<>();

    //Unidirectional mapping for cooking steps
    @OneToMany
    @JoinColumn(name = "recipe_id")
    private Set<CookingStep> cookingSteps = new HashSet<>();

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}