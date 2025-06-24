package com.adobe.prj.entity;

import com.adobe.prj.annotations.Column;
import com.adobe.prj.annotations.Table;

@Table(name="BOOKS")
public class Book {
    private String isbn;
    private double price;


    @Column(name="BOOK_ISBN")
    public String getIsbn() {
        return isbn;
    }

    @Column(name="AMT", type = "NUMERIC(12,2)")
    public double getPrice() {
        return price;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
