package com.slgproduction.mealapp.service;

import com.slgproduction.mealapp.model.Recipe;
import com.slgproduction.mealapp.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public List<Recipe> getRecipes() {
        return recipeRepository.findAll();
    }

    public Recipe createNewRecipe() {
        return new Recipe();
    }

    public  Recipe findById(Long id) {
        return  recipeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No question with id " + id + " found"));
    }

    public Long save(Recipe recipe){
        return recipeRepository.save(recipe).getId();
    }
}
