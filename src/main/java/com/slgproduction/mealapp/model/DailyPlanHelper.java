package com.slgproduction.mealapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyPlanHelper {
    String recipeName;
    String dateString;
}
