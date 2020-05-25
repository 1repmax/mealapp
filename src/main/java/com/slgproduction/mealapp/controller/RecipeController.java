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
@RequestMapping("${application.context}/${application.version}/recipe")
public class RecipeController {

    private final RecipeService recipeService;
    private final CookingStepService cookingStepService;
    private final IngredientService ingredientService;
    private final ProductService productService;

    // Controller called on pushing button Add new recipe
    @GetMapping("new")
    public ModelAndView createNewRecipe(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("addRecipe");

        Recipe recipe = recipeService.createNewRecipe();
        CookingStep cookingStep = cookingStepService.createNewCookingStep();
        Ingredient ingredient = ingredientService.createNewIngredient();
        Long recipeId = recipeService.save(recipe);

        session.setAttribute("recipeId", recipeId);
        modelAndView.addObject("recipe", recipe);
        modelAndView.addObject("cookingStep", cookingStep);
        modelAndView.addObject("ingredient", ingredient);
        return modelAndView;
    }

    // Controller called after removing recipe. Remove recipe involves http redirect
    // so we need GET method
    @GetMapping("addStep")
    public ModelAndView afterRemoveCookingStep(HttpSession session){
        ModelAndView modelAndView = new ModelAndView("addRecipe");
        Long recipeId = (Long) session.getAttribute("recipeId");

        Recipe recipe = recipeService.findById(recipeId);

        modelAndView.addObject("recipe", recipe);
        modelAndView.addObject("cookingStep", new CookingStep());
        modelAndView.addObject("ingredient", new Ingredient());
        return modelAndView;
    }

    // Controller for adding Cooking steps to recipe
    @PostMapping("addStep")
    public ModelAndView addCookingStep(@ModelAttribute Recipe recipe, @ModelAttribute CookingStep cookingStep, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("addRecipe");
        Long recipeId = (Long) session.getAttribute("recipeId");

        Recipe temporaryRecipe = recipeService.findById(recipeId);
        temporaryRecipe.setName(recipe.getName());
        temporaryRecipe.setCookingTime(recipe.getCookingTime());
        temporaryRecipe.setShortDescription(recipe.getShortDescription());
        recipeService.addCookingStep(temporaryRecipe, cookingStep);
        recipeService.save(temporaryRecipe);

        modelAndView.addObject("recipe", temporaryRecipe);
        modelAndView.addObject("cookingStep", new CookingStep());
        modelAndView.addObject("ingredient", new Ingredient());
        return modelAndView;
    }


    // Controller for removing cooking steps
    @PostMapping("deleteStep")
    public ModelAndView deleteLastCookingStep(@ModelAttribute Recipe recipe, @ModelAttribute CookingStep cookingStep, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("redirect:addStep");
        Long recipeId = (Long) session.getAttribute("recipeId");

        Recipe tempRecipe = recipeService.findById(recipeId);
        recipeService.removeLastStep(recipeId);
        recipeService.save(tempRecipe);

        modelAndView.addObject("recipe", recipe);
        modelAndView.addObject("cookingStep", cookingStep);
        return modelAndView;
    }

    // Controller for adding ingredients to recipe
    @PostMapping("addIngredient")
    public ModelAndView addIngredient(@ModelAttribute Recipe recipe, @ModelAttribute Ingredient ingredient, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("addRecipe");
        Long recipeId = (Long) session.getAttribute("recipeId");

        Recipe temporaryRecipe = recipeService.findById(recipeId);
        temporaryRecipe.setName(recipe.getName());
        temporaryRecipe.setCookingTime(recipe.getCookingTime());
        temporaryRecipe.setShortDescription(recipe.getShortDescription());
        ingredientService.addIngredient(temporaryRecipe, ingredient);
        recipeService.save(temporaryRecipe);

        modelAndView.addObject("recipe", temporaryRecipe);
        modelAndView.addObject("cookingStep", new CookingStep());
        modelAndView.addObject("ingredient", new Ingredient());
        return modelAndView;
    }

    @PostMapping("deleteIngredient")
    public ModelAndView deleteLastInredient(@ModelAttribute Recipe recipe, @ModelAttribute Ingredient ingredient, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("redirect:addStep");
        Long recipeId = (Long) session.getAttribute("recipeId");

        Recipe tempRecipe = recipeService.findById(recipeId);
        recipeService.removeLastIngredient(recipeId);
        recipeService.save(tempRecipe);

        modelAndView.addObject("recipe", recipe);
        modelAndView.addObject("ingredient", ingredient);
        return modelAndView;
    }


    // Save command is only for redirect purposes, as recipe is saved on creation
    @PostMapping("complete")
    public ModelAndView saveRecipe(@ModelAttribute Recipe recipe, @ModelAttribute CookingStep cookingStep,
                                   @ModelAttribute Ingredient ingredient, HttpSession session) {
        Long recipeId = (Long) session.getAttribute("recipeId");

        Recipe temporaryRecipe = recipeService.findById(recipeId);
        temporaryRecipe.setName(recipe.getName());
        temporaryRecipe.setCookingTime(recipe.getCookingTime());
        temporaryRecipe.setShortDescription(recipe.getShortDescription());
//        recipeService.addCookingStep(temporaryRecipe, cookingStep);
//        ingredientService.addIngredient(temporaryRecipe, ingredient);
        recipeService.save(temporaryRecipe);

        return new ModelAndView("redirect:all");
    }

    @GetMapping("delete")
    public ModelAndView deleteRecipe(HttpSession session){
        Long recipeId = (Long) session.getAttribute("recipeId");
        Recipe recipe = recipeService.findById(recipeId);

        recipeService.delete(recipe);
        return new ModelAndView("redirect:all");
    }


    @GetMapping("{recipeId}")
    public ModelAndView viewRecipe(@PathVariable Long recipeId, HttpSession session){
        session.setAttribute("recipeId", recipeId);
        ModelAndView modelAndView = new ModelAndView("recipe");
        Recipe recipe = recipeService.findById(recipeId);
        modelAndView.addObject("recipe", recipe);
        return modelAndView;
    }

    // Mapping to home.html for testing purposes
    @GetMapping(value = "all")
    public ModelAndView showRecipes() {
        ModelAndView modelAndView = new ModelAndView("recipes");
        List<Recipe> recipes = recipeService.getRecipes();
        modelAndView.addObject("recipes", recipes);
        return modelAndView;
    }
}
