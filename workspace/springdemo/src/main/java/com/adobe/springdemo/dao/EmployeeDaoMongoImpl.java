package com.adobe.springdemo.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("prod")
public class EmployeeDaoMongoImpl implements  EmployeeDao{
    @Override
    public void addEmployee() {
        System.out.println("Mongo Store!!!");
    }
}
