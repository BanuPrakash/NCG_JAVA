package com.adobe.springdemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDaoJdbcImpl implements  EmployeeDao{
    @Override
    public void addEmployee() {
        System.out.println("JDBC Store!!!");
    }
}
