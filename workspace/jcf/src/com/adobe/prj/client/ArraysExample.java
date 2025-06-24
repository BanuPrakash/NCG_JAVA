package com.adobe.prj.client;

import com.adobe.prj.entity.Product;

import java.util.Arrays;
import java.util.Comparator;

class LengthComparator implements  Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        return 0;
    }
}

public class ArraysExample {
    public static void main(String[] args) {
        String[] names = {"Harry", "Kim", "Peter", "Hathaway", "Angelina", "Scarlett", "Brad"};

        Arrays.sort(names); // Comparable

        for(String name: names) {
            System.out.println(name);
        }

        System.out.println("*******");
        //  Arrays.sort(names, new LengthComparator());

        // Anonymous class
        Arrays.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        for(String name: names) {
            System.out.println(name);
        }

        System.out.println("*******");

        Product[] products = new Product[5];
        products[0] = new Product(92, "Samsung Fold", 1_20_000.00, "mobile");
        products[1] = new Product(71, "LG AC", 45000.00, "electronics");
        products[2] = new Product(76, "Wacom", 4000.00, "computer");
        products[3] = new Product(12, "iPhone", 99_000.00, "mobile");
        products[4] = new Product(34, "Logitech Mouse", 800.00, "computer");

        Arrays.sort(products);

        for(Product p: products) {
            System.out.println(p);
        }

        System.out.println("**** NAME ***");

//        Arrays.sort(products, new Comparator<Product>() {
//            @Override
//            public int compare(Product o1, Product o2) {
//                return o1.getName().compareTo(o2.getName());
//            }
//        });
        // lambda expression
//        Arrays.sort(products, (Product o1, Product o2) -> {
//            return o1.getName().compareTo(o2.getName());
//        });

//        Arrays.sort(products, (Product o1, Product o2) -> o1.getName().compareTo(o2.getName()));

        // Type Inference
        Arrays.sort(products, ( o1,  o2) -> o1.getName().compareTo(o2.getName()));

        for(Product p: products) {
            System.out.println(p);
        }
    }
}
