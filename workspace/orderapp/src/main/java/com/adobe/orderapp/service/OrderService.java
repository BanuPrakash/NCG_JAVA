package com.adobe.orderapp.service;

import com.adobe.orderapp.dto.NameAndPrice;
import com.adobe.orderapp.entity.Customer;
import com.adobe.orderapp.entity.Product;
import com.adobe.orderapp.repo.CustomerDao;
import com.adobe.orderapp.repo.ProductDao;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    // constructor DI instead of setter DI
//    @Autowired
    private final ProductDao productDao; // generated implementation class is wired

//    @Autowired
    private final CustomerDao customerDao;// generated implementation class is wired

    public Product getProductById(int id) {
        Optional<Product> opt =  productDao.findById(id);
        if(opt.isPresent()) {
            return opt.get();
        }
        return null;
    }
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

    // DTO record
    public List<NameAndPrice> byNameAndPrice() {
        return  productDao.getNameAndPrice();
    }

    @Transactional
    public Product modifyProduct(int id, double price) {
        productDao.updateProduct(id, price);
        return  getProductById(id);
    }
}
