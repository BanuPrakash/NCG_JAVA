package com.adobe.prj.client;

import com.adobe.prj.entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamExample {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Product(92, "Samsung Fold", 1_20_000.00, "mobile"));
        products.add( new Product(71, "LG AC", 45000.00, "electronics"));
        products.add(new Product(76, "Wacom", 4000.00, "computer"));
        products.add(new Product(12, "iPhone", 99_000.00, "mobile"));
        products.add(new Product(34, "Logitech Mouse", 800.00, "computer"));

        products.stream()
                .filter(p -> p.getCategory().equals("mobile"))
                .forEach(p -> System.out.println(p));

        System.out.println("*****");
        products.stream()
                .filter(p -> p.getCategory().equals("mobile"))
                .map(p -> p.getName()) // transform
                .forEach(name -> System.out.println(name));

        System.out.println("*****");

       double total =  products.parallelStream()
               .filter(p -> p.getCategory().equals("mobile"))
                .map(p -> p.getPrice())
                .reduce(0.0, (v1, v2) -> v1 + v2);
        System.out.println(total);


        Map<String, List<Product>> productCat = products.stream()
                .collect(Collectors.groupingBy(p -> p.getCategory()));


        List<Product> mobiles = productCat.get("mobile");
        for(Product p : mobiles) {
            System.out.println(p);
        }

        System.out.println("******");

        productCat.forEach((k,v) -> {
            System.out.println("Category : " + k);
            for(Product p : v) {
                System.out.println(p);
            }
        });
    }
}
