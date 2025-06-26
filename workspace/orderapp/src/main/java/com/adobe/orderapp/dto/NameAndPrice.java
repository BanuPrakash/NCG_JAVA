package com.adobe.orderapp.dto;

// record is for DTO, contains constructor, getters; no setters
public record NameAndPrice(String name, double price) {
}
//
//public class NameAndPrice {
//    private String name;
//    private double price;
//    // const, getters and setters
//}