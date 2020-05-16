package com.slgproduction.mealapp.controller;

import com.slgproduction.mealapp.model.Ingredient;
import com.slgproduction.mealapp.model.Recipe;
import com.slgproduction.mealapp.service.IngredientService;
import com.slgproduction.mealapp.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${application.context}/${application.version}")
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping(value = "/recipes")
    public List<Recipe> getRecipes() {
        return recipeService.getRecipes();
    }

    // Mapping to home.html for testing purposes
    @GetMapping(value = "/home")
    public ModelAndView showRecipes() {
        ModelAndView modelAndView = new ModelAndView("home");
        List<Recipe> recipes = recipeService.getRecipes();
        modelAndView.addObject("recipes", recipes);
        return modelAndView;
    }
}
