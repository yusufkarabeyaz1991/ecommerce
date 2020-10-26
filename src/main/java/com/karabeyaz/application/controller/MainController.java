package com.karabeyaz.application.controller;

import com.karabeyaz.application.model.Product;
import com.karabeyaz.application.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class MainController {

    private final ProductRepository productRepository;

    public MainController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Login form
    @RequestMapping("/")
    public String index() {
        return "login";
    }

    // Login form
    @RequestMapping("/login.html")
    public String login() {
        return "redirect:list";
    }

    // Login form with error
    @RequestMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @GetMapping(value = "list")
    public String results(Model model) {

        Map<String, List<Product>> productMap = StreamSupport.stream(productRepository.findAll().spliterator(), false)
                                                    .collect(Collectors.groupingBy(Product::getCategory));
        model.addAttribute("items", productMap);
        return "productlist";
    }

    @RequestMapping("/addProduct")
    public String addProduct(Product product, Model model) {
        productRepository.save(product);
        return "redirect:list";
    }
}