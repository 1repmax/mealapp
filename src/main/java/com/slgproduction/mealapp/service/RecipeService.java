package com.slgproduction.mealapp.service;

import com.slgproduction.mealapp.model.CookingStep;
import com.slgproduction.mealapp.model.Ingredient;
import com.slgproduction.mealapp.model.Product;
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
    private final CookingStepService cookingStepService;
    private final IngredientService ingredientService;

    public List<Recipe> getRecipes() {
        return recipeRepository.findAll();
    }

    public  Recipe findById(Long id) {
        return  recipeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No question with id " + id + " found"));
    }

    public List<Recipe> getRecipeByName(String name){
        return recipeRepository.findByName(name);
    }

    public Recipe createNewRecipe() {
        return new Recipe();
    }

    public Long save(Recipe recipe){
        return recipeRepository.save(recipe).getId();
    }

    public void addCookingStep(Recipe recipe, CookingStep cookingStep) {
        List<CookingStep> cookingSteps = recipe.getCookingSteps();
        cookingStep.setStepNumber(cookingSteps.size() + 1);
        cookingSteps.add(cookingStep);
    }

    public  void removeLastStep(Long id) {
       List<CookingStep> cookingSteps = recipeRepository.findById(id).get().getCookingSteps();
       CookingStep cookingStep = cookingSteps.get(cookingSteps.size()-1);
       cookingSteps.remove(cookingStep);
       cookingStepService.delete(cookingStep);
    }

    public void removeLastIngredient(Long id) {
        List<Ingredient> ingredients = recipeRepository.findById(id).get().getIngredients();
        Ingredient ingredient = ingredients.get(ingredients.size()-1);
        ingredients.remove(ingredient);
        ingredientService.delete(ingredient);
    }

    public void delete(Recipe recipe){
        recipeRepository.delete(recipe);
    }

}
