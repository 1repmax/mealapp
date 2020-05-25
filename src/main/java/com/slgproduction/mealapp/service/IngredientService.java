package com.slgproduction.mealapp.service;

import com.slgproduction.mealapp.model.*;
import com.slgproduction.mealapp.repository.IngredientRepository;
import com.slgproduction.mealapp.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final ProductService productService;
    private final UnitService unitService;

    public List<Ingredient> getIngredients() {
        return ingredientRepository.findAll();
    }

    public Ingredient createNewIngredient() {
        return new Ingredient();
    }

    public void addIngredient(Recipe recipe, Ingredient ingredient) {

        String productName = ingredient.getProduct().getName();
        String unitDenotation = ingredient.getUnit().getDenotation();

        if (!productService.getProductsByName(ingredient.getProduct().getName()).isEmpty()){
            Product product = productService.getProductsByName(productName).get(0);
            ingredient.setProduct(product);
        } else {
            productService.save(ingredient.getProduct());
        }

        if(!unitService.getUnitByDenotation(unitDenotation).isEmpty()){
            Unit unit = unitService.getUnitByDenotation(unitDenotation).get(0);
            ingredient.setUnit(unit);
        } else {
            unitService.save(ingredient.getUnit());
        }

        ingredient.setRecipe(recipe);
        List<Ingredient> ingredients = recipe.getIngredients();
        ingredients.add(ingredient);
    }

    public void delete(Ingredient ingredient){ ingredientRepository.delete(ingredient);}

}
