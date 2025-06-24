package com.adobe.prj.client;

import com.adobe.prj.entity.Mobile;
import com.adobe.prj.entity.Product;
import com.adobe.prj.entity.Tv;
import com.adobe.prj.utility.Utility;

public class SortClient {
    public static void main(String[] args) {
        String[] names = {"George", "Angelina", "Anne", "Brad", "Clooney", "Tom"};
        Product[] products = new Product[4]; // Array of Pointers
        products[0] = new Mobile(82, "Samsung Fold", 98000.00, "5G");
        products[1] = new Tv(62, "Onida Thunder", 3500.00, "CRT");

        products[2] = new Mobile(13, "MotoG", 9000.00, "5G");
        products[3] = new Tv(81, "Sony Bravia", 2_40_000.00, "OLED");

        Utility.sort(names);
        Utility.sort(products);

        for(String name: names) {
            System.out.println(name);
        }

        for(Product p : products) {
            System.out.println(p);
        }
    }
}
