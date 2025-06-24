package com.adobe.prj.client;

import com.adobe.prj.entity.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListExample {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>(); // typesafe
        names.add("Hathaway");
        names.add("Brad");
        names.add("Lee");
        names.add("George");

        List<Product> products = new ArrayList<>();
        products.add(new Product(92, "Samsung Fold", 1_20_000.00, "mobile"));
        products.add( new Product(71, "LG AC", 45000.00, "electronics"));
        products.add(new Product(76, "Wacom", 4000.00, "computer"));
        products.add(new Product(12, "iPhone", 99_000.00, "mobile"));
        products.add(new Product(34, "Logitech Mouse", 800.00, "computer"));


//        Collections.sort(names);
        Collections.sort(names, (s1,s2) -> s1.length() - s2.length());

        Collections.sort(products, (p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));

        printdata(names);
        printdata(products);

    }

//    private static void printdata(List<Object> data) {
    // ? unknown type
private static void printdata(List<?> data) {
        for(Object obj: data) {
            System.out.println(obj);
        }
    }
}
