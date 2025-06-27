package com.adobe.orderapp.api;

import com.adobe.orderapp.dto.OrderReport;
import com.adobe.orderapp.entity.Order;
import com.adobe.orderapp.exception.EntityNotFoundException;
import com.adobe.orderapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/orders")
public class OrderController {
    private final OrderService service;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody Order order) throws EntityNotFoundException {
        return  service.placeOrder(order);
    }

    // GET http://localhost:8080/api/orders
    // GET http://localhost:8080/api/orders?order-date=2025-06-27
    @GetMapping()
    public List<Order> getOrders(@RequestParam(name = "order-date", required = false)
                                     @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        if(date == null) {
            return service.getOrders();
        } else {
            return service.getOrdersByDate(date);
        }
    }


    // GET http://localhost:8080/api/orders/report
    @GetMapping("/report")
    public List<OrderReport> getReport() {
        return  service.getReport();
    }
}
