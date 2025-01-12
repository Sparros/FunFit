package com.funfit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
//     String URL = "jdbc:mysql://localhost:3306/funfit";
//     String USER = "root";
//     String PASSWORD = "password";

    public static Connection getConnection() {
        Connection con = null;
        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish connection
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/funfit", "root", "password");
            System.out.println("Database connection established.");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error establishing database connection. Please check your credentials and URL.");
            e.printStackTrace();
        }
        return con;
    }
}
