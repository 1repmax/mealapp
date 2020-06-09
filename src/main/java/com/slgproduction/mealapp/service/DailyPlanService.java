package com.slgproduction.mealapp.service;

import com.slgproduction.mealapp.model.DailyPlan;
import com.slgproduction.mealapp.model.Product;
import com.slgproduction.mealapp.model.User;
import com.slgproduction.mealapp.repository.DailyPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class DailyPlanService {

    private final DailyPlanRepository dailyPlanRepository;

    public DailyPlan findById(Long id) {return dailyPlanRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No dailyPlan by id  " + id + " found"));}

    public List<DailyPlan> getDailyPlans() {
        return dailyPlanRepository.findAll();
    }

    public List<DailyPlan> getUserDailyPlans(User user) {
        return dailyPlanRepository.findByUser(user);
    }

    public DailyPlan createNewDailyPlan() {return new DailyPlan();}

    public void save(DailyPlan dailyPlan) {dailyPlanRepository.save(dailyPlan);}

    public void delete(DailyPlan dailyPlan) {dailyPlanRepository.delete(dailyPlan);}

}
