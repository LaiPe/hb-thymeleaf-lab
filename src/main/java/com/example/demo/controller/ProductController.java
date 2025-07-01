package com.example.demo.controller;
import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Product;



@Controller
public class ProductController {

    private final List<Product> products = new ArrayList<>(
        List.of(
            new Product(1L, "Laptop", 999.99),
            new Product(2L, "Smartphone", 499.99),
            new Product(3L, "Tablet", 299.99),
            new Product(4L, "Smartwatch", 199.99),
            new Product(5L, "Headphones", 89.99)
        )
    );
    private Long counter = (long) products.size() ;

    @GetMapping("/products")
    public String getProducts(Model model) {
        model.addAttribute("productList", products);
        return "products";
    }

    @GetMapping("/products/add")
    public String addProduct(Model model) {
        model.addAttribute("product", new Product());
        return "add-product";
    }

    @PostMapping("/products/add")
    public String saveProduct(@Valid @ModelAttribute Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-product";
        }

        products.add(product);
        return "redirect:/products";
    }



    
    
}
