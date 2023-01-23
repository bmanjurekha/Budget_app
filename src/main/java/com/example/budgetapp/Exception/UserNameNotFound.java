package com.example.budgetapp.Exception;

public class UserNameNotFound extends RuntimeException {

    private String username;

    public UserNameNotFound(String message, String username) {
        super(message);

        this.username = username;
    }


    public String getUsername() {
        return username;
    }

}
