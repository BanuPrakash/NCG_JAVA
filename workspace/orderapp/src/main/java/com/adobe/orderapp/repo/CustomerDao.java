package com.adobe.orderapp.repo;

import com.adobe.orderapp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDao extends JpaRepository<Customer, String> {
}
