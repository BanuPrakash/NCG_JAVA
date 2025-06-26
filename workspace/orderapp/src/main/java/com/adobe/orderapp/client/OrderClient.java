package com.adobe.orderapp.client;

import com.adobe.orderapp.entity.Customer;
import com.adobe.orderapp.entity.LineItem;
import com.adobe.orderapp.entity.Order;
import com.adobe.orderapp.entity.Product;
import com.adobe.orderapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderClient implements CommandLineRunner {
    private final OrderService service; // ConstructorDI

    @Override
    public void run(String... args) throws Exception {
//        newOrder();
        getOrders();
    }

    private void getOrders() {
        List<Order> orders = service.getOrders();
        for(Order order: orders) {
            System.out.println(order.getOrderDate() + " ," + order.getCustomer().getFirstName() + " , " + order.getTotal());
            List<LineItem> items = order.getItems();
            for (LineItem item : items) {
                System.out.println(item.getProduct().getName() + ", " + item.getQty() + ", " + item.getAmount());
            }
        }
    }

    private void newOrder() {
        Order order = new Order();
        Customer customer = new Customer();
        customer.setEmail("roger@adobe.com");
        order.setCustomer(customer); // order is by given customer

        LineItem item1 = new LineItem();
        item1.setProduct(Product.builder().id(2).build());
        item1.setQty(3);

//        LineItem item2 = new LineItem();
//        item2.setProduct(Product.builder().id(1).build());
//        item2.setQty(2);

        order.getItems().add(item1);
//        order.getItems().add(item2);

        service.placeOrder(order);
    }
}
