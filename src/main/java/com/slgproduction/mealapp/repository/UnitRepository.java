package com.slgproduction.mealapp.repository;

import com.slgproduction.mealapp.model.Product;
import com.slgproduction.mealapp.model.Recipe;
import com.slgproduction.mealapp.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {
    List<Unit> findByDenotation(String product);
}
