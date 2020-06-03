package com.slgproduction.mealapp.controller;

import com.slgproduction.mealapp.model.*;
import com.slgproduction.mealapp.service.*;
import lombok.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController

@RequiredArgsConstructor
//@RequestMapping("${application.context}/${application.version}")
@SessionAttributes("test")
public class RecipeController {

    private final RecipeService recipeService;
    private final CookingStepService cookingStepService;

    @GetMapping("/recipe")
    public ModelAndView createNewRecipe(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("addRecipe");
        Recipe recipe = recipeService.createNewRecipe();
        CookingStep cookingStep = cookingStepService.createNewCookingStep();
        Long recepId = recipeService.save(recipe);
        session.setAttribute("recipeId", recepId);
        modelAndView.addObject("recipe", recipe);
        modelAndView.addObject("cookingStep", cookingStep);
        return modelAndView;
    }

    @PostMapping("/recipe")
    public ModelAndView addCookingStep(@ModelAttribute Recipe recipe, @ModelAttribute CookingStep cookingStep, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("addRecipe");
        Long recipeId = (Long) session.getAttribute("recipeId");
        Recipe temporaryRecipe = recipeService.findById(recipeId);
        temporaryRecipe.setName(recipe.getName());
        temporaryRecipe.setCookingTime(recipe.getCookingTime());
        temporaryRecipe.setShortDescription(recipe.getShortDescription());
        temporaryRecipe.addCookingStep(cookingStep);
        Long some = recipeService.save(temporaryRecipe);
        modelAndView.addObject("recipe", temporaryRecipe);
        modelAndView.addObject("cookingStep", new CookingStep());
        return modelAndView;
    }

    @PostMapping("/recipe/save")
    public ModelAndView saveRecipe(@ModelAttribute Recipe recipe, HttpSession session) {
        return new ModelAndView("redirect:/recipes");
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
