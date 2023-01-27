package com.example.budgetapp.Controller;

import com.example.budgetapp.Exception.InvalidPassword;
import com.example.budgetapp.Exception.UserNameNotFound;
import com.example.budgetapp.Model.Invoice;
import com.example.budgetapp.Model.Payment;
import com.example.budgetapp.Services.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
@Controller
@RequestMapping("/invoice")
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController() {
        paymentService = new PaymentService();
    }
    @ExceptionHandler(UserNameNotFound.class)
    public String handleInvalidUserName(UserNameNotFound ex, HttpSession session, HttpServletRequest req) {

        Object loginAttempts = session.getAttribute("loginAttempts");
        if (loginAttempts == null) {
            loginAttempts = 0;
        }

        session.setAttribute("loginAttempts", (int) loginAttempts + 1);

        return "redirect:login?error=" + ex.getMessage();
    }
    @GetMapping()
    public String showInvoice(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if(!(username.isBlank() || username.isEmpty())) {
            Invoice userInvoice = paymentService.getInvoice(username);
            model.addAttribute("items", userInvoice.getInvoiceItems());
            Invoice invoice = (Invoice) session.getAttribute("spayment");
            if(invoice==null ) {
                Payment pay = new Payment();
                invoice = new Invoice(username);
                invoice.getInvoiceItems().add(pay);
            }
            model.addAttribute("dpayment",invoice);
            return "invoicePage";
        }
        else {
            throw new UserNameNotFound("Invalid Employee Name", username);
        }
    }
    @PostMapping()
    public String addItem(HttpSession session, @ModelAttribute Payment paymentItem) {
        String username = (String) session.getAttribute("username");

        paymentService.addPaymentItem(username, paymentItem);

        return "redirect:/invoice";
    }
    @GetMapping("/delete")
    public String deleteItem(int id)
    {
        paymentService.deletePaymentItem(id);
        return "redirect:/invoice";
    }
    @GetMapping("/edit")
    public String editItem(HttpSession session, Model model, int id)
    {
        String username = (String) session.getAttribute("username");
        Invoice editinvoice = paymentService.editPaymentItem(username,id);
        if(editinvoice!=null) {
           System.out.println("isEmpty - "+ editinvoice.getInvoiceItems().isEmpty());
            System.out.println("category - "+ editinvoice.getInvoiceItems().get(0).getCategory());
            System.out.println("title - "+ editinvoice.getInvoiceItems().get(0).getTitle());
            System.out.println("invoicedate - "+ editinvoice.getInvoiceItems().get(0).getInvoicedate());
            System.out.println("desc - "+ editinvoice.getInvoiceItems().get(0).getDescription());
            System.out.println("amount - "+ editinvoice.getInvoiceItems().get(0).getAmount());
            System.out.println("id - "+ editinvoice.getInvoiceItems().get(0).getId());


           Payment pay = new Payment();
           pay.setId(editinvoice.getInvoiceItems().get(0).getId());
           pay.setInvoicedate(editinvoice.getInvoiceItems().get(0).getInvoicedate());
            pay.setDescription(editinvoice.getInvoiceItems().get(0).getDescription());
            pay.setCategory(editinvoice.getInvoiceItems().get(0).getCategory());
            pay.setTitle(editinvoice.getInvoiceItems().get(0).getTitle());
            pay.setAmount(editinvoice.getInvoiceItems().get(0).getAmount());
           Invoice in = new Invoice(username);
           in.getInvoiceItems().add(pay);
           model.addAttribute("dpayment",in);
           session.setAttribute("spayment", in);
       }
        return "invoicePage";
    }

}
