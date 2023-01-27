package com.example.budgetapp.Model;

import java.sql.Date;

public class Payment {
    private  int id;
    private String title;
    private Date invoicedate;
    private String description;
    private String category;
    private int amount;

    public int getId(){return id;}
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public Date getInvoicedate() {
        return invoicedate;
    }

    public int getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setInvoicedate(Date invoicedate) {
        this.invoicedate = invoicedate;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void setId(int id){this.id = id;}
}
