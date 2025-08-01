package com.adobe.springdemo.cfg;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    // @Bean --> factory method
    @Bean()
//    @Scope("prototype")
    public DataSource getDataSource() {
        try {
            ComboPooledDataSource cpds = new ComboPooledDataSource();
            cpds.setDriverClass("com.mysql.cj.jdbc.Driver"); //loads the jdbc driver
            cpds.setJdbcUrl("jdbc:mysql://localhost:3306/JAVA_NCG");
            cpds.setUser("root");
            cpds.setPassword("Welcome123");
            cpds.setMinPoolSize(5);
            cpds.setAcquireIncrement(5);
            cpds.setMaxPoolSize(20);
            return cpds;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return  null;
    }


}
