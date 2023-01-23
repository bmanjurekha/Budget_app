package com.example.budgetapp.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDatabase {
    private static MySqlDatabase instance;
    private Connection connection;

    private MySqlDatabase() {
        String url = "jdbc:mysql://localhost:3306/budget_app";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, "root", "");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static MySqlDatabase getInstance() {
        if(instance == null) {
            instance = new MySqlDatabase();
        }

        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
