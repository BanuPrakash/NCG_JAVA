package com.adobe.springdemo.service;

import com.adobe.springdemo.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AppService {
    @Autowired
    private EmployeeDao employeeDao; // loosely coupled

    public  void insert() {
        employeeDao.addEmployee();
    }
}
