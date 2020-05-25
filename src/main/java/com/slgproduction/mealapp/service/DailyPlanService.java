package com.slgproduction.mealapp.service;

import com.slgproduction.mealapp.model.DailyPlan;
import com.slgproduction.mealapp.model.Product;
import com.slgproduction.mealapp.repository.DailyPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyPlanService {

    private final DailyPlanRepository dailyPlanRepository;

    public List<DailyPlan> getDailyPlans() {
        return dailyPlanRepository.findAll();
    }

    public DailyPlan createNewDailyPlan() {return new DailyPlan();}

    public void save(DailyPlan dailyPlan) {dailyPlanRepository.save(dailyPlan);}

}
