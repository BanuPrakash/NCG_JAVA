package com.adobe.orderapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message="Name is required!!!")
    private String name;


    @Min(value = 10, message = "Price ${validatedValue} has to be more than {value}")
    private  double price;

    @Min(value = 1, message = "Quantity ${validatedValue} has to be more than {value}")
    @Column(name="qty")
    private int quantity;
}
