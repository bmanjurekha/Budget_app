package com.example.budgetapp.Controller;

import com.example.budgetapp.Exception.InvalidPassword;
import com.example.budgetapp.Services.AuthService;
import com.example.budgetapp.Services.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
    @RequestMapping("/auth/*")
    public class AuthController {
    private AuthService authService;
    public  AuthController()
    {
        authService = new AuthService();
    }

    @ExceptionHandler(InvalidPassword.class)
    public String handleInvalidPassword(InvalidPassword ex, HttpSession session, HttpServletRequest req) {

        Object loginAttempts = session.getAttribute("loginAttempts");
        if (loginAttempts == null) {
            loginAttempts = 0;
        }

        session.setAttribute("loginAttempts", (int) loginAttempts + 1);

        return "redirect:login?error=" + ex.getMessage();
    }

    @GetMapping("Employee")
    public String employee() {
        return "employeePage";
    }

    @PostMapping("login")
    public String register(HttpSession session, RedirectAttributes redirect, @RequestParam String username, @RequestParam String password) {
         if (authService.isEmployee(username, password)) {
             session.setMaxInactiveInterval(60 * 30);
             session.setAttribute("username", username);
             return "redirect:/invoice";
        } else {
            throw new InvalidPassword("Invalid Employee Name or Password", username);
        }
    }
    @PostMapping("logout")
    public String logout(HttpSession session) throws IOException {
        session.invalidate(); //Invalidate - empty the session
        return "redirect:/Index.html";
    }
}