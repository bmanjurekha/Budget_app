package com.example.budgetapp.Services;

import com.example.budgetapp.Repository.AuthRepository;

public class AuthService {
    private AuthRepository authRepository;

    public AuthService() {
        this.authRepository = new AuthRepository();
    }
    public boolean isEmployee(String username,String password) {
        return authRepository.isEmployee(username,password);
    }
}
