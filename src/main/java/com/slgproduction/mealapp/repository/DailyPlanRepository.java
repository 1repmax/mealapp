package com.slgproduction.mealapp.repository;

import com.slgproduction.mealapp.model.DailyPlan;
import com.slgproduction.mealapp.model.Ingredient;
import com.slgproduction.mealapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DailyPlanRepository extends JpaRepository<DailyPlan, Long> {
    List<DailyPlan> findByUser(User user);
}
