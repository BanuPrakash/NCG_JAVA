package com.adobe.prj.client;

import com.adobe.prj.entity.Mobile;
import com.adobe.prj.entity.Product;
import com.adobe.prj.entity.Tv;

import java.lang.reflect.Method;

public class ProductClient {
    public static void main(String[] args) {
        Product[] products = new Product[4]; // Array of Pointers
        products[0] = new Mobile(82, "Samsung Fold", 98000.00, "5G");
        products[1] = new Tv(62, "Onida Thunder", 3500.00, "CRT");

        products[2] = new Mobile(13, "MotoG", 9000.00, "5G");
        products[3] = new Tv(81, "Sony Bravia", 2_40_000.00, "OLED");
//        products[4] = new Product() ; // 'Product' is abstract; cannot be instantiated
        printExpensive(products);
        // printDetails(products);
        printDetailsOCP(products);
    }

    //OCP ,reflection API
    private static void printDetailsOCP(Product[] products) {
        for(Product p : products) {
            Method[] methods = p.getClass().getMethods();
            for(Method m : methods) {
                if(m.getName().startsWith("get")) {
                    try {
                        Object ret = m.invoke(p);
                        System.out.println(m.getName().substring(3) + " : " + ret);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
            System.out.println("******");
        }
    }

    // OCP ???
    private static void printDetails(Product[] products) {
        for(Product p : products) {
            System.out.println(p.getName() + ", " + p.getPrice() );
            if(p instanceof Mobile) {
                Mobile m = (Mobile) p;
                System.out.println(m.getConnectivity());
            }
            if(p.getClass() == Tv.class) {
                Tv t = (Tv) p;
                System.out.println(t.getScreenType());
            }
        }
    }


    // OCP
    private static void printExpensive(Product[] products) {
        for(Product p : products) {
            if(p.isExpensive()) { // polymorphism, dynamic binding
                System.out.println(p.getName() + " is expensive!!!");
            } else {
                System.out.println(p.getName() + " is not expensive!!!");
            }
        }
    }


}
