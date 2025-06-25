package com.adobe.prj.client;

import com.adobe.prj.dao.ProductDao;
import com.adobe.prj.dao.ProductDaoJdbcImpl;
import com.adobe.prj.entity.Product;

import java.util.List;

public class ListProducts {
    public static void main(String[] args) {
        ProductDao productDao = new ProductDaoJdbcImpl();
        List<Product> products = productDao.getProducts();
        for(Product p : products) {
            System.out.println(p);
        }
    }
}
