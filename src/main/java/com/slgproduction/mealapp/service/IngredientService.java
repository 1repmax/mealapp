package com.slgproduction.mealapp.service;

import com.slgproduction.mealapp.model.Ingredient;
import com.slgproduction.mealapp.model.Product;
import com.slgproduction.mealapp.repository.IngredientRepository;
import com.slgproduction.mealapp.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

@AllArgsConstructor
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    public List<Ingredient> getIngredients() {
        return ingredientRepository.findAll();
    }
}
