package io.github.mortenjenne.fridgechef.util;

import java.sql.*;

public class DatabaseWriter extends DatabaseConnector{
    private DatabaseReader dbReader = new DatabaseReader();


    public boolean createAccount (String email, String accountName, String password) {
        connect();
        String sql = "INSERT INTO accounts (email, accountName, password) values (?, ?, ?)";


        if (dbReader.checkExistingAccount(email)) {
            System.out.println("Email is already in use, please try another!");
            return false;
        } else {
            try {
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, accountName);
                stm.setString(3, password);
                int rowsUpdated = stm.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("Account created - OK");
                    return true;
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }

        return false;
    }

}
