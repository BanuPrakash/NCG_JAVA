package com.adobe.orderapp.service;

import com.adobe.orderapp.dto.NameAndPrice;
import com.adobe.orderapp.dto.OrderReport;
import com.adobe.orderapp.entity.Customer;
import com.adobe.orderapp.entity.LineItem;
import com.adobe.orderapp.entity.Order;
import com.adobe.orderapp.entity.Product;
import com.adobe.orderapp.repo.CustomerDao;
import com.adobe.orderapp.repo.OrderDao;
import com.adobe.orderapp.repo.ProductDao;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    private final OrderDao orderDao;

    /*
        orderDate ---> System date,
        total --> compute
        {
            "customer": {"email":"rita@adobe.com"},
            "items": [
                {"product": {"id": 4 }, "qty": 2}, // amount --> computed
                {"product": {"id": 1}, "qty": 1}
            ]
        }
     */
    // order has to be inserted, line items has to be inserted, product qty has to be decreased

    //@Transactional // atomic operation
    public String placeOrder(Order order) {
        List<LineItem> items = order.getItems();
        double total = 0.0;
        for(LineItem item : items) {
            Product p = getProductById(item.getProduct().getId()); // complete data from DB
            if(p.getQuantity() < item.getQty()) {
                throw  new IllegalArgumentException("Product not in Stock!!! : " + p.getName());
            }
            // DIRTY CHECKING, withing @Transactional if entity becomes dirty
            // automatically JPA sends UPDATE SQL
            p.setQuantity(p.getQuantity() - item.getQty());// reduce inventory qty
            item.setAmount(p.getPrice() * item.getQty()); // tax , discount
            total += item.getAmount();
        }
        order.setTotal(total);
        orderDao.save(order); // cascade takes care of inserting line items.
        return  "Order placed!!!";
    }

    public List<Order> getOrders() {
        return  orderDao.findAll();
    }


    public List<OrderReport> getReport() {
        return  orderDao.getReport();
    }

    public List<Order> getOrdersByDate(Date d) {
        return orderDao.getOrderForGivenDate(d);
    }

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

    public List<Product> byRange(double low, double high) {
        return productDao.fetchByRange(low, high);
    }
}
