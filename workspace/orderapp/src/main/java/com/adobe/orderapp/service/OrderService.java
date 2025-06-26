package com.adobe.orderapp.service;

import com.adobe.orderapp.entity.Customer;
import com.adobe.orderapp.entity.Product;
import com.adobe.orderapp.repo.CustomerDao;
import com.adobe.orderapp.repo.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private ProductDao productDao; // generated implementation class is wired

    @Autowired
    private CustomerDao customerDao;// generated implementation class is wired

    public List<Product> getProducts() {
        return productDao.findAll();
    }

    public List<Customer> getCustomers() {
        return  customerDao.findAll();
    }

    public Product addProduct(Product p) {
        return  productDao.save(p);
    }

    public Customer addCustomer(Customer c) {
        return  customerDao.save(c);
    }

    public long getCustomerCount() {
        return  customerDao.count();
    }
}
