package com.adobe.springdemo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
@Profile("dev")
public class EmployeeDaoJdbcImpl implements  EmployeeDao{
    @Autowired
    DataSource dataSource; // pool of database connection
    @Override
    public void addEmployee() {
        try {
            Connection con = dataSource.getConnection(); // get from pool of connection
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select * from products");
            while(rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        System.out.println("JDBC Store!!!");
    }
}

// new EmployeeDaoJdbcImpl(); not required, Spring container instantiates because of @Repository