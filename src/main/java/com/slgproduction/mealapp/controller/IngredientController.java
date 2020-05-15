package com.slgproduction.mealapp.controller;

import com.slgproduction.mealapp.model.Ingredient;
import com.slgproduction.mealapp.model.Product;
import com.slgproduction.mealapp.service.IngredientService;
import com.slgproduction.mealapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${application.context}/${application.version}")
public class IngredientController {
    private final IngredientService ingredientService;

    @GetMapping(value = "/ingredients")
    public List<Ingredient> getIngredient() {

        List<Ingredient> ingredients = ingredientService.getIngredients();
        return ingredientService.getIngredients();
    }
}
