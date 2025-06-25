package com.adobe.prj.client;

import com.adobe.prj.dao.PersistenceException;
import com.adobe.prj.dao.ProductDao;
import com.adobe.prj.dao.ProductDaoJdbcImpl;
import com.adobe.prj.entity.Product;

public class AddClient {
    public static void main(String[] args) {
        Product product = new Product();
        product.setName("Macbook Pro");
        product.setPrice(2_10_000.00);

        ProductDao productDao = new ProductDaoJdbcImpl();
        try {
            productDao.addProduct(product);
            System.out.println("Product added!!!");
        } catch (PersistenceException e) {
            System.out.println(e.getMessage()); // END USER
            e.printStackTrace(); // FOR DEVELOPER, use only in DEV env and not in PRODUCTION
        }
    }
}
