package com.example.budgetapp.Repository;

import com.example.budgetapp.DB.MySqlDatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthRepository {
    private MySqlDatabase db;

    public AuthRepository() {
        db = MySqlDatabase.getInstance();
    }
    public boolean isEmployee(String username,String password) {
        Connection conn = db.getConnection();
        String sql = "SELECT * FROM  employee WHERE name=? AND password=?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2,password);

            ResultSet rs = pstmt.executeQuery();

            if(!rs.next()) { return false; } //guardian clause

            System.out.println(username);
            String name="";
            do {
                name = rs.getString("name");
            } while(rs.next());

            return !(name.isEmpty() || name.isBlank());


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
