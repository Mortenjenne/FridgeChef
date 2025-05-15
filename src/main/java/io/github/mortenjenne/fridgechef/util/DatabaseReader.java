package io.github.mortenjenne.fridgechef.util;

import java.sql.*;
import java.util.List;

public class DatabaseReader extends DatabaseConnector{

    public boolean accountLogin (String email, String password){
        connect();

        String sql = "SELECT password FROM accounts WHERE email = ?";
        //? is a placeholder in the sql statement, we later input the correct values through setString.
        //This prevents SQL injections like "email = ' OR '1'='1", this would NOT be good!
        //Following sql command would run: SELECT password FROM accounts WHERE email = '' OR '1'='1'

        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1,email); //Prevents SQL Injections by setting placeholder value

            ResultSet rs = stm.executeQuery();
            if (rs.next()) { //This if checks for email existence
                String tmpPassword = rs.getString("password");

                if (password.equals(tmpPassword)) {
                    System.out.println("Account login success - OK");
                    return true;
                } else {
                    System.out.println("Email or password is incorrect");
                    return false;
                }


            } else {
                System.out.println("Email not found");
                return false;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }


    //      ------  GETTERS  ------
    public int getAccountId(String email) {
        connect();
        String sql = "SELECT accountID FROM accounts WHERE email = ?";
        int value = 0;
        try{
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1,email);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                System.out.println("getAccountID has next");
                value = rs.getInt("accountID"); //Returns accountID if account email exists
            }

        } catch (SQLException e){
            System.out.println("Error checking for accountID: "+e.getMessage());
        }
        return value;

    }

    public int getAccountFridgeID(int accountID){
        connect();
        String sql = "SELECT fridgeID FROM fridges WHERE accountID = ?";
        int value = 0;
        try{
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1,accountID);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                value = rs.getInt("fridgeID"); //Returns fridgetID if account email exists
            }

        } catch (SQLException e){
            System.out.println("Error checking for fridgeID: "+e.getMessage());
        }
        return value;
    }

    public String getAccountName(String email) {
        connect();

        String sql = "SELECT accountName FROM accounts WHERE email = ?";

        try{
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1,email);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                rs.getString("accountName"); //Returns fridgetID if account email exists
            }

        } catch (SQLException e){
            System.out.println("Error checking for accountname: "+e.getMessage());
        }
        return null;

    }

    public List<Integer> getAccountIngredients(String email){

        return null;
    }


    //      ------  CHECKERS  ------
    public boolean checkExistingAccount (String email){
        connect();
        String sql = "SELECT email FROM accounts WHERE email = ?";

        try{
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1,email);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                return true; //Returns true if account exist
            }

        } catch (SQLException e){
            System.out.println("Error checking for existing accounts: "+e.getMessage());
        }
        return false; //Returns false if account does NOT exist
    }

    public boolean checkExistingIngredient(int ingredientID) {
        connect();

        String sql = "SELECT ingredientID FROM ingredients WHERE ingredientID = ?";
        try{
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1,ingredientID);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                return true; //Returns true if ingredient exist
            }

        } catch (SQLException e){
            System.out.println("Error checking for existing ingredients: "+e.getMessage());
        }
        return false; //Returns false if ingredient does NOT exist
    }
}
