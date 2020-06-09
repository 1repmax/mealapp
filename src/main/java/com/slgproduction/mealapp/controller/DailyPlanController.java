package com.slgproduction.mealapp.controller;

import com.slgproduction.mealapp.model.*;
import com.slgproduction.mealapp.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${application.context}/${application.version}/plans")
public class DailyPlanController {

    private final DailyPlanService dailyPlanService;
    private final RecipeService recipeService;
    private final UserService userService;
    private final BasketService basketService;

    @GetMapping(value = "")
    public ModelAndView getPlans(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("dailyPlans");

        User user = userService.getSessionUser(principal.getName());
        List<DailyPlan> dailyPlans = dailyPlanService.getUserDailyPlans(user);
        List<Basket> baskets = basketService.getUserBaskets(user);

        DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;

        String pattern = "MM-dd-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        BasketHelper basketHelper = new BasketHelper();

//        modelAndView.addObject("productBaskets", productBaskets);
        modelAndView.addObject("basketHelper", basketHelper);
        modelAndView.addObject("baskets", baskets);
        modelAndView.addObject("simpleDateFormat", simpleDateFormat);
        modelAndView.addObject("dailyPlans", dailyPlans);
        return modelAndView;
    }

    @GetMapping(value = "new")
    public ModelAndView newPlan(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("dailyPlanAdd");
        List<DailyPlan> dailyPlans = dailyPlanService.getDailyPlans();
        List<Recipe> recipes = recipeService.getRecipes();

        DailyPlanHelper dailyPlan = new DailyPlanHelper();

        modelAndView.addObject("recipes", recipes);
        modelAndView.addObject("dailyPlan", dailyPlan);
        return modelAndView;
    }

    @GetMapping(value = "{planId}/delete")
    public ModelAndView deleteDailyPlan(@PathVariable Long planId){
        ModelAndView modelAndView = new ModelAndView("redirect:/mealapp/v1/plans");
        DailyPlan dailyPlan = dailyPlanService.findById(planId);
        dailyPlanService.delete(dailyPlan);
        return modelAndView;
    }

    @PostMapping(value = "new/basket")
    public ModelAndView newBasket(@ModelAttribute BasketHelper basketHelper, Principal principal) throws ParseException {
        ModelAndView modelAndView = new ModelAndView("redirect:/mealapp/v1/plans");
        User user = userService.getSessionUser(principal.getName());

        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(basketHelper.getStartDate());
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(basketHelper.getEndDate());

        Basket basket = basketService.createNewBasket();
        basket.setStartDate(startDate);
        basket.setEndDate(endDate);
        basket.setUser(user);
        basketService.save(basket);

        return modelAndView;
    }

    @PostMapping(value = "save")
    public ModelAndView savePlan(@ModelAttribute DailyPlanHelper dailyPlanHelper, Principal principal) throws ParseException {
        ModelAndView modelAndView = new ModelAndView("redirect:/mealapp/v1/plans");
        DailyPlan dailyPlan = dailyPlanService.createNewDailyPlan();

        Recipe recipe = recipeService.getRecipeByName(dailyPlanHelper.getRecipeName()).get(0);
        dailyPlan.setRecipe(recipe);

        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dailyPlanHelper.getDateString());
        dailyPlan.setTargetDate(date);

        User user = userService.getSessionUser(principal.getName());
        dailyPlan.setUser(user);

        dailyPlanService.save(dailyPlan);

        return modelAndView;
    }

    @GetMapping(value = "basket/{basketId}")
    public ModelAndView viewBasket(@PathVariable Long basketId, Principal principal){
        ModelAndView modelAndView = new ModelAndView("basket");
        User user = userService.getSessionUser(principal.getName());
        Basket basket = basketService.findById(basketId);

        List<DailyPlan> dailyPlans = dailyPlanService.getUserDailyPlans(user);
        List<ProductBasket> productBaskets= new ArrayList<>();

        for (DailyPlan dailyPlan: dailyPlans) {
            int comparedToStart = dailyPlan.getTargetDate().compareTo(basket.getStartDate());
            int comparedToEnd = dailyPlan.getTargetDate().compareTo(basket.getEndDate());

            if( comparedToStart >= 0 && comparedToEnd <= 0){
                Recipe recipe = dailyPlan.getRecipe();
                for(Ingredient ingredient: recipe.getIngredients()){

                    boolean addNew = true;

                    for (ProductBasket productBasket:productBaskets){
                        if (productBasket.getProductName().equals(ingredient.getProduct().getName())
                                && productBasket.getUnitName().equals(ingredient.getUnit().getDenotation())) {
                            productBasket.setQuantity(ingredient.getQuantity() + productBasket.getQuantity());
                            addNew = false;
                            break;
                        }
                    }

                    if(addNew){
                        ProductBasket productBasket = new ProductBasket(ingredient.getProduct().getName(), ingredient.getQuantity(), ingredient.getUnit().getDenotation());
                        productBaskets.add(productBasket);
                    }

                }
            }
        }

        modelAndView.addObject("productBaskets", productBaskets);
        return modelAndView;
    }

    @GetMapping(value = "basket/{basketId}/delete")
    public ModelAndView deleteBasket(@PathVariable Long basketId){
        ModelAndView modelAndView = new ModelAndView("redirect:/mealapp/v1/plans");
        Basket basket = basketService.findById(basketId);
        basketService.delete(basket);

        return modelAndView;
    }

}
