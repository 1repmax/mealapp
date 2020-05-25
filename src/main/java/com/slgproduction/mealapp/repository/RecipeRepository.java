package com.slgproduction.mealapp.repository;

import com.slgproduction.mealapp.model.Product;
import com.slgproduction.mealapp.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByName(String recipe);
}
