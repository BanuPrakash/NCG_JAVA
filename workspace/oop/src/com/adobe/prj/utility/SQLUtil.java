package com.adobe.prj.utility;

import com.adobe.prj.annotations.Column;
import com.adobe.prj.annotations.Table;

import java.lang.reflect.Method;

public class SQLUtil {
    // create table BOOKS (BOOK_ISBN VARCHAR(100), AMT NUMERIC(12,2));
    //clazz can be Book.class, Employee.class, Product.class
    // ? unknown type, allows accessor, prevents mutation
    public static  String createStatement(Class<?> clazz) {
        StringBuilder builder = new StringBuilder(); // allows mutation
        Table table = clazz.getAnnotation(Table.class);
        if(table != null) {
            builder.append("create table ");
            builder.append(table.name()); // create table BOOKS
            builder.append("(");
            Method[] methods = clazz.getDeclaredMethods();
            for(Method  m : methods) {
                if(m.getName().startsWith("get")) {
                    Column col = m.getAnnotation(Column.class);
                    if(col != null) {

                        builder.append(col.name());
                        builder.append(" ");
                        builder.append(col.type());
                        builder.append(",");
                    }
                }
            }

            builder.setCharAt(builder.lastIndexOf(","),')');
        }
        return  builder.toString();
    }

    // Book b = new Book("AW113", 900.11");
    // insert into BOOKS values ( 'AW113', 900.11);
    public String insertStatement(Object obj) {

        return "";
    }
}
