package com.slgproduction.mealapp.service;

import com.slgproduction.mealapp.model.Product;
import com.slgproduction.mealapp.model.Unit;
import com.slgproduction.mealapp.repository.UnitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UnitService {

    private final UnitRepository unitRepository;

    public List<Unit> getUnitByDenotation(String denotation){
        return unitRepository.findByDenotation(denotation);
    }

    public Unit createNewUnit() {
        return new Unit();
    }

    public void save(Unit unit){
        unitRepository.save(unit);
    }
}
