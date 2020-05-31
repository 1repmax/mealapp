package com.slgproduction.mealapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "recipes", schema = "mealdb")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "cooking_time")
    private Integer cookingTime;

    @Column(name = "usage_frequency")
    private Integer usageFrequency;

    @Column(name = "servings_per_recipe")
    private Integer servingsPerRecipe;

    @OneToMany (mappedBy = "recipe", cascade = CascadeType.ALL)
    private Set<Ingredient> ingredients = new HashSet<>();

    @OneToMany (cascade = CascadeType.ALL)
    @JoinColumn(name = "recipe_id")
    private Set<DailyPlan> dailyPlans = new HashSet<>();

    //Unidirectional mapping for cooking steps
//    @OneToMany (cascade = {CascadeType.ALL})
//    @JoinColumn(name = "recipe_id")
//    private Set<CookingStep> cookingSteps = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "recipe_id")
    private List<CookingStep> cookingSteps = new ArrayList<>();

    public void addCookingStep(){
        cookingSteps.add(new CookingStep());
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
