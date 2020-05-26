package com.slgproduction.mealapp.controller;

import com.slgproduction.mealapp.model.*;
import com.slgproduction.mealapp.service.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${application.context}/${application.version}")
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping("/recipe")
    public ModelAndView createNewRecipe() {
        ModelAndView modelAndView = new ModelAndView("addRecipe");
        Recipe recipe = recipeService.createNewRecipe();
        modelAndView.addObject("recipe", recipe);
        return modelAndView;
    }

    @PostMapping("/recipe")
    public void saveRecipe() {

    }

    // Mapping to home.html for testing purposes
    @GetMapping(value = "/recipes")
    public ModelAndView showRecipes() {
        ModelAndView modelAndView = new ModelAndView("recipes");
        List<Recipe> recipes = recipeService.getRecipes();
        modelAndView.addObject("recipes", recipes);
        return modelAndView;
    }
}
