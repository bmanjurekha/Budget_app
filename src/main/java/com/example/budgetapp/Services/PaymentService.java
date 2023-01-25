package com.example.budgetapp.Services;

import com.example.budgetapp.Model.Invoice;
import com.example.budgetapp.Repository.PaymentRepository;

public class PaymentService {

    private PaymentRepository paymentRepository;

    public PaymentService() {
        this.paymentRepository = new PaymentRepository();
    }

    public Invoice getInvoice(String username) {
        Invoice list = paymentRepository.getInvoice(username);

        if(list == null) {
            list = new Invoice(username);
        }

        return list;
    }

}
