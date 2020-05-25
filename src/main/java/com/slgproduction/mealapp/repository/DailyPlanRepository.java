package com.slgproduction.mealapp.repository;

import com.slgproduction.mealapp.model.DailyPlan;
import com.slgproduction.mealapp.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyPlanRepository extends JpaRepository<DailyPlan, Long> {
}
