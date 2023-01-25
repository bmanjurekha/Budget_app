package com.example.budgetapp.Controller;

import com.example.budgetapp.Exception.InvalidPassword;
import com.example.budgetapp.Exception.UserNameNotFound;
import com.example.budgetapp.Model.Invoice;
import com.example.budgetapp.Services.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
            return "invoicePage";
        }
        else {
            throw new UserNameNotFound("Invalid Employee Name", username);
        }
    }

}
