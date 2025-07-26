package com.home.task.unity.utils;

import java.sql.*;

public class DatabaseUtils {
    Connection conn = null;

    public DatabaseUtils(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void exeQuery() {
        String url = "jdbc:mysql://adminjs-example-mysql:3306/adminjs";
        String user = "root";
        String password = "adminjs";
        try {
            conn = DriverManager.getConnection(url, user, password);

            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM adminjs.Publisher;";
            ResultSet rs = stmt.executeQuery(sql);
            rs = stmt.getResultSet();
            System.out.println(rs);
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

}
