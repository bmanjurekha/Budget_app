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
                item.setId(rs.getInt("id"));
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

    public void addItem(String username, Payment item) {
        Connection conn = db.getConnection();
        String sql = "SELECT id FROM employee WHERE name=?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            int employeeId;

            if(!rs.next()) {
                employeeId = 0;
            } else {
                employeeId = rs.getInt("id");
            }

            sql = "INSERT INTO invoice (title, category, invoicedate,description,amount,employeeid)" +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, item.getTitle());
            pstmt.setString(2, item.getCategory());
            pstmt.setDate(3, item.getInvoicedate());
            pstmt.setString(4, item.getDescription());
            pstmt.setInt(5, item.getAmount());
            pstmt.setInt(6, employeeId);

            pstmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteItem(int id)
    {
        Connection conn = db.getConnection();
        String sql = "DELETE FROM invoice WHERE id=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Invoice editItem(String username, int id)
    {
        Connection conn = db.getConnection();
        Invoice lstitem = new Invoice(username);
        String sql = "SELECT * FROM invoice WHERE id=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if(!rs.next()) { return null; } //guardian clause


            do {
                Payment item = new Payment();
                item.setId(id);
                item.setTitle(rs.getString("title"));
                item.setCategory(rs.getString("category"));
                item.setInvoicedate(rs.getDate("invoicedate"));
                item.setAmount(rs.getInt("amount"));
                item.setDescription(rs.getString("description"));
                lstitem.getInvoiceItems().add(item);
            } while(rs.next());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lstitem;
    }
}
