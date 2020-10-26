package com.karabeyaz.application;

import com.karabeyaz.application.model.Product;
import com.karabeyaz.application.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class TrendyolApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrendyolApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(ProductRepository productRepository) throws Exception {
        return (String[] args) -> {
            Product product1 = new Product("table", new BigDecimal(20), "furniture");
            Product product2 = new Product("chair", new BigDecimal(30), "furniture");
            productRepository.save(product1);
            productRepository.save(product2);
            productRepository.findAll().forEach(product -> System.out.println(product.getTitle()));
        };
    }
}
