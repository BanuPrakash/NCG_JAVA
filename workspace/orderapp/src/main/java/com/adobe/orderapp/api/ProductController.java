package com.adobe.orderapp.api;

import com.adobe.orderapp.entity.Product;
import com.adobe.orderapp.exception.EntityNotFoundException;
import com.adobe.orderapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/products")
public class ProductController {
    private final OrderService service;

    // GET http://localhost:8080/api/products
    // GET http://localhost:8080/api/products?low=5000.15&high=25000
    @GetMapping()
    public List<Product> getProducts(@RequestParam(name="low", defaultValue = "0.0") double low,
                                     @RequestParam(name="high" , defaultValue = "0.0") double high) {
        if(low == 0.0 && high == 0.0) {
            return service.getProducts();
        } else {
            return service.byRange(low, high);
        }
    }

    // http://localhost:8080/api/products/3
    @GetMapping("/{pid}")
    public Product getProductById(@PathVariable("pid") int id) throws EntityNotFoundException {
        return service.getProductById(id);
    }

    // POST http://localhost:8080/api/products
    // Accept: application/json
    // Content-type:application/json
    // payload
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED) // 201
    public Product addProduct(@RequestBody Product product) {
        return  service.addProduct(product);
    }

    @PatchMapping ("/{id}")
    public Product updateProduct(@PathVariable("id") int id, @RequestParam("price") double price) throws EntityNotFoundException{
       return service.modifyProduct(id, price);
    }
}
