package com.adobe.prj.orderapp.api;

import com.adobe.prj.orderapp.entity.Order;
import com.adobe.prj.orderapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;

    @GetMapping
    public List<Order> getOrders(@RequestParam(name = "order-date", required = false) String orderDate) {
        // public List<Order> getOrders(@RequestParam(name = "order-date", required = false)
        // @DateTimeFormat(pattern = "yyyy-MM-dd") Date orderDate) {
        if(orderDate == null) {
            return service.getOrders();
        } else {
            return service.getByDate(orderDate);
        }
    }



    @PostMapping
    public String placeOrder(@RequestBody Order o) {
        return service.placeOrder(o);
    }
}
