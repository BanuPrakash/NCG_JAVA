package com.adobe.orderapp.dto;

import java.util.Date;

public record OrderReport(String firstName, String email, Date orderDate, double total) {
}
