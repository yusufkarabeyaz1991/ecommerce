package com.karabeyaz.application.controller;

import com.karabeyaz.application.model.Product;
import com.karabeyaz.application.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class EcommerceController {

    private final ProductRepository productRepository;

    public EcommerceController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @PostMapping("/products")
    Product newEmployee(@RequestBody Product newProduct) {
        return productRepository.save(newProduct);
    }

    @GetMapping("/products")
    Map<String, List<Product>> all() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false)
                .collect(Collectors.groupingBy(Product::getCategory));
    }
}