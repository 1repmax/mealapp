package com.slgproduction.mealapp.controller;

import com.slgproduction.mealapp.model.*;
import com.slgproduction.mealapp.service.DailyPlanService;
import com.slgproduction.mealapp.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${application.context}/${application.version}/plans")
public class DailyPlanController {

    private final DailyPlanService dailyPlanService;
    private final RecipeService recipeService;

    @GetMapping(value = "")
    public ModelAndView getPlans() {
        ModelAndView modelAndView = new ModelAndView("dailyPlans");
        List<DailyPlan> dailyPlans = dailyPlanService.getDailyPlans();
        modelAndView.addObject("dailyPlans", dailyPlans);
        return modelAndView;
    }

    @GetMapping(value = "new")
    public ModelAndView newPlan(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("dailyPlanAdd");
        List<DailyPlan> dailyPlans = dailyPlanService.getDailyPlans();
        List<Recipe> recipes = recipeService.getRecipes();

//        DailyPlan dailyPlan = dailyPlanService.createNewDailyPlan();

        DailyPlanHelper dailyPlan = new DailyPlanHelper();

        modelAndView.addObject("recipes", recipes);
        modelAndView.addObject("dailyPlan", dailyPlan);
        return modelAndView;
    }

    @PostMapping(value = "save")
    public ModelAndView savePlan(@ModelAttribute DailyPlanHelper dailyPlanHelper) throws ParseException {
        ModelAndView modelAndView = new ModelAndView("redirect:");
        DailyPlan dailyPlan = dailyPlanService.createNewDailyPlan();

        Recipe recipe = recipeService.getRecipeByName(dailyPlanHelper.getRecipeName()).get(0);
        dailyPlan.setRecipe(recipe);

        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dailyPlanHelper.getDateString());
        dailyPlan.setTargetDate(date);

        dailyPlanService.save(dailyPlan);

        return modelAndView;
    }

}
