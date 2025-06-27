package com.adobe.orderapp.repo;

import com.adobe.orderapp.dto.OrderReport;
import com.adobe.orderapp.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface OrderDao extends JpaRepository<Order, Integer> {

//    @Query(nativeQuery = true,
//            value = "select c.fname, c.email, o.order_date, o.total  " +
//                    " from orders o inner join customers c " +
//                    " on c.email = o.customer_fk")
//    List<Object[]> getReport();

    @Query("select new com.adobe.orderapp.dto.OrderReport(c.firstName, c.email, o.orderDate, o.total)" +
            " from Order o " +
            " inner join o.customer c")
    List<OrderReport> getReport();

    @Query("from Order where DATE(orderDate) = :od")
    List<Order> getOrderForGivenDate(@Param("od") Date orderDate);

}
