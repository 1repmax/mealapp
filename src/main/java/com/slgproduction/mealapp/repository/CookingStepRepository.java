package com.slgproduction.mealapp.repository;

import com.slgproduction.mealapp.model.CookingStep;
import com.slgproduction.mealapp.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CookingStepRepository extends JpaRepository<CookingStep, Long> {
}
