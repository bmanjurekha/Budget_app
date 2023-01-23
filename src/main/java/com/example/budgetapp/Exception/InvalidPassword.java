package com.example.budgetapp.Exception;

public class InvalidPassword extends RuntimeException {

    private String username;

    public InvalidPassword(String message, String username) {
        super(message);

        this.username = username;
    }


    public String getUsername() {
        return username;
    }

}
