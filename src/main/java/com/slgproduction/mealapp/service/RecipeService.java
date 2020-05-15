package com.slgproduction.mealapp.service;

import com.slgproduction.mealapp.model.Ingredient;
import com.slgproduction.mealapp.model.Recipe;
import com.slgproduction.mealapp.repository.IngredientRepository;
import com.slgproduction.mealapp.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public List<Recipe> getRecipes() {
        return recipeRepository.findAll();
    }

}
