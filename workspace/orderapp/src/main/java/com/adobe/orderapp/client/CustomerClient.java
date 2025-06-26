package com.adobe.orderapp.client;

import com.adobe.orderapp.entity.Customer;
import com.adobe.orderapp.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(1)
public class CustomerClient implements CommandLineRunner {
    @Autowired
    private OrderService service;

    @Override
    public void run(String... args) throws Exception {
//        addCustomers();
//        listCustomers();
    }

    private void listCustomers() {
        List<Customer> customers = service.getCustomers();
        for(Customer c : customers) {
            System.out.println(c);
        }
    }

    private void addCustomers() {
        if(service.getCustomerCount() == 0) {
            service.addCustomer(new Customer("roger@adobe.com", "Roger", "Smith"));
            service.addCustomer(new Customer("rita@adobe.com", "Rita", "Kumari"));
            service.addCustomer(new Customer("priya@adobe.com", "Priya", "Sharma"));
        }
    }
}
