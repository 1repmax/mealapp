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
@RequestMapping("${application.context}/${application.version}")
@SessionAttributes("test")
public class RecipeController {

    private final RecipeService recipeService;
    private final CookingStepService cookingStepService;

    @GetMapping("/recipe")
    public ModelAndView createNewRecipe(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("addRecipe");
        session.setAttribute("myAttribute", 1);
        Recipe recipe = recipeService.createNewRecipe();
        CookingStep cookingStep = new CookingStep();

        Long recepId = recipeService.save(recipe);
        session.setAttribute("recipeId", recepId);

        modelAndView.addObject("test", new Test());
        modelAndView.addObject("recipe", recipe);
        modelAndView.addObject("cookingStep", cookingStep);
        return modelAndView;
    }

    @PostMapping("/recipe")
    public ModelAndView saveRecipe(@ModelAttribute Recipe recipe, @ModelAttribute CookingStep cookingStep, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("addRecipe");
        Long recipeId = (Long) session.getAttribute("recipeId");

        Recipe temporaryRecipe = recipeService.findById(recipeId);
        temporaryRecipe.setName(recipe.getName());
        temporaryRecipe.setCookingTime(recipe.getCookingTime());
        temporaryRecipe.setShortDescription(recipe.getShortDescription());

        System.out.println(temporaryRecipe);
        System.out.println(cookingStep);
        temporaryRecipe.addCookingStep(cookingStep);
        Long some = recipeService.save(temporaryRecipe);

        modelAndView.addObject("recipe", temporaryRecipe);
        modelAndView.addObject("cookingStep", cookingStep);

        return modelAndView;
    }

//    @PostMapping("/recipe/save")
//    public ModelAndView svRecipe(@ModelAttribute Recipe recipe) {
//        ModelAndView modelAndView = new ModelAndView("addRecipe");
//        Integer value = (Integer) session.getAttribute("myAttribute");
//        session.setAttribute("myAttribute", value +1);
//        Long recipeId = (Long) session.getAttribute("recipeId");
//
//        Recipe recipe = recipeService.findById(recipeId);
//        CookingStep cookingStep = new CookingStep();
//        cookingStep.setRecipeId(recipe.getId());
//        cookingStepService.save(cookingStep);


//        if (recipe.getCookingSteps().size() < value){
//            recipe.addCookingStep();
//            Long recipeId2 = recipeService.save(recipe);
////        }
//        modelAndView.addObject("recipe", recipe);

//
//        if (test != null) {
//            Integer temp = test.getCounter();
//        }
//        Integer temp = test.getCounter();
//        test.setCounter(temp);
//
//
//        attributes.addFlashAttribute(test);
//        modelAndView.addObject("test", test);
//        return modelAndView;
//    }

    // Mapping to home.html for testing purposes
    @GetMapping(value = "/recipes")
    public ModelAndView showRecipes() {
        ModelAndView modelAndView = new ModelAndView("recipes");
        List<Recipe> recipes = recipeService.getRecipes();
        modelAndView.addObject("recipes", recipes);
        return modelAndView;
    }

//    @ModelAttribute("test")
//    public Test test(){
//        return new Test();
//    }

}
