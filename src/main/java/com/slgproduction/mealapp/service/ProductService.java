package com.slgproduction.mealapp.service;

import com.slgproduction.mealapp.model.Ingredient;
import com.slgproduction.mealapp.model.Product;
import com.slgproduction.mealapp.model.Recipe;
import com.slgproduction.mealapp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByName(String name){
        return  productRepository.findByName(name);
    }

    public Product createNewProduct() {
        return new Product();
    }

    public void save(Product product){
         productRepository.save(product);
    }
}
