package com.example.budgetapp.Model;

import java.util.ArrayList;
import java.util.List;

public class Invoice {

    private List<Payment> invoiceItems;
    private String employee;

    public Invoice(String employee) {
        this.employee = employee;
        this.invoiceItems = new ArrayList<>();
    }

    public String getEmployee() {
        return employee;
    }

    public List<Payment> getInvoiceItems() {
        return invoiceItems;
    }
}
