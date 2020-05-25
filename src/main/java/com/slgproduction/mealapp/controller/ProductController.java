package com.slgproduction.mealapp.controller;

import com.slgproduction.mealapp.model.Product;
import com.slgproduction.mealapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
//@RequestMapping("${application.context}/${application.version}")
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "/products")
    public List<Product> getProduct() {
        return productService.getProducts();
    }

}
