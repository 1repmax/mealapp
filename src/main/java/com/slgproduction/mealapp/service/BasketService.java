package com.slgproduction.mealapp.service;


import com.slgproduction.mealapp.model.Basket;
import com.slgproduction.mealapp.model.DailyPlan;
import com.slgproduction.mealapp.model.User;
import com.slgproduction.mealapp.repository.BasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BasketService {
    private final BasketRepository basketRepository;

    public List<Basket> getBaskets() { return basketRepository.findAll();}

    public List<Basket> getUserBaskets(User user) { return  basketRepository.findByUser(user); }

    public Basket findById(Long id){ return basketRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No basket by id  " + id + " found")); }

    public Basket createNewBasket() {
        return new Basket();
    }

    public void save(Basket basket) {basketRepository.save(basket);}

    public void delete(Basket basket){basketRepository.delete(basket);}
}
