/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author GK
 */
public class Transaction {

    private final String username;
    private final String type;
    private final double amount;
    private final String category;
    private final LocalDate date;

    public Transaction(String username,
                       String type,
                       double amount,
                       String category) {

        this.username = username;
        this.type = type;
        this.amount = amount;
        this.category = category;
        this.date = LocalDate.now();
    }

    public String getUsername() {
        return username;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public LocalDate getDate() {
        return date;
    }
}