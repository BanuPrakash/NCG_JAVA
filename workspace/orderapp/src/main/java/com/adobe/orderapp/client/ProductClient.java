package com.adobe.orderapp.client;


import com.adobe.orderapp.dto.NameAndPrice;
import com.adobe.orderapp.entity.Product;
import com.adobe.orderapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Order(2)
public class ProductClient implements CommandLineRunner {
//    @Autowired
    private final OrderService service;
    @Override
    public void run(String... args) throws Exception {
//        getProducts();
//        productById(3);
//        addNewProduct();
//        fetchNameAndPrice();
        modifyProduct();
    }

    private void modifyProduct() {
        Product p = service.modifyProduct(2, 5356.99);
        System.out.println(p);
    }

    private void fetchNameAndPrice() {
        List<NameAndPrice> records = service.byNameAndPrice();
        for(NameAndPrice dto : records) {
            System.out.println(dto.name() +" ," + dto.price());
        }
    }

    private void addNewProduct() {
        Product p = Product.builder()
                .name("iPad")
                .price(45000.00)
                .quantity(100)
                .build();
        System.out.println(service.addProduct(p));
    }

    private void productById(int id) {
        System.out.println("By ID : " + id);
        System.out.println(service.getProductById(id));
    }

    private void getProducts() {
        List<Product> products = service.getProducts();
        for(Product p : products) {
            System.out.println(p);
        }
    }


}
