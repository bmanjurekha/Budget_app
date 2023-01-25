package com.example.budgetapp.Repository;

import com.example.budgetapp.DB.MySqlDatabase;
import com.example.budgetapp.Model.Invoice;
import com.example.budgetapp.Model.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentRepository {
    private MySqlDatabase db;

    public PaymentRepository() {
        db = MySqlDatabase.getInstance();
    }

    public Invoice getInvoice(String username) {
        Connection conn = db.getConnection();
        Invoice list = new Invoice(username);
        String sql = "" +
                "SELECT * FROM invoice " +
                "JOIN employee " +
                "ON invoice.employeeid=employee.id " +
                "WHERE employee.name = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();

            if(!rs.next()) { return null; } //guardian clause

            System.out.println(username);
            do {
                Payment item = new Payment();
                item.setTitle(rs.getString("title"));
                item.setCategory(rs.getString("category"));
                item.setInvoicedate(rs.getDate("invoicedate"));
                item.setAmount(rs.getInt("amount"));
                item.setDescription(rs.getString("description"));

                list.getInvoiceItems().add(item);
            } while(rs.next());


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }
}
