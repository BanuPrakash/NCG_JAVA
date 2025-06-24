package com.adobe.prj.client;

import com.adobe.prj.entity.Book;
import com.adobe.prj.utility.SQLUtil;

public class SQLClient {
    public static void main(String[] args) {
        String SQL = SQLUtil.createStatement(Book.class);
        System.out.println(SQL);
    }
}
