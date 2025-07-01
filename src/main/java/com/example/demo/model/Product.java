package com.example.demo.model;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long id;

    @NotEmpty(message = "Le nom ne doit pas être nul")
    private String name;

    @NotNull(message = "Le prix du produit ne peut pas être nul")
    @DecimalMin(value = "0.01", message = "Le prix doit être supérieur ou égal à 0.01")
    private Double price;

    
    
}
