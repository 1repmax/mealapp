package com.slgproduction.mealapp.service;

import com.slgproduction.mealapp.model.CookingStep;
import com.slgproduction.mealapp.model.Ingredient;
import com.slgproduction.mealapp.model.Recipe;
import com.slgproduction.mealapp.repository.CookingStepRepository;
import com.slgproduction.mealapp.repository.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CookingStepService {

    private final CookingStepRepository cookingStepRepository;

    public List<CookingStep> getCookingSteps() { return cookingStepRepository.findAll();}

    public CookingStep createNewCookingStep() {
        return new CookingStep();
    }

    public void save(CookingStep cookingStep){
        cookingStepRepository.save(cookingStep);
    }

    public void delete(CookingStep cookingStep) {cookingStepRepository.delete(cookingStep);}

}




