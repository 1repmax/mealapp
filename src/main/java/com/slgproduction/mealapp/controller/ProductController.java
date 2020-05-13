package com.slgproduction.mealapp.controller;

import com.slgproduction.mealapp.model.Product;
import com.slgproduction.mealapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("${application.context}/${application.version}")
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "/products")
    @ResponseBody
    public List<Product> getProduct() {
        return productService.getProducts();
    }
}
