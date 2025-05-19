package io.github.mortenjenne.fridgechef.util;

import io.github.mortenjenne.fridgechef.model.Account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseReader extends DatabaseConnector{

    public Account accountLogin (String email, String password){
        connect();

        String sql = "SELECT * FROM accounts WHERE email = ? AND password = ?";
        //? is a placeholder in the sql statement, we later input the correct values through setString.
        //This prevents SQL injections like "email = ' OR '1'='1", this would NOT be good!
        //Following sql command would run: SELECT password FROM accounts WHERE email = '' OR '1'='1'

        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, email); //Prevents SQL Injections by setting placeholder value
            stm.setString(2, password);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) { //This if checks for account existence

                int accountID = rs.getInt("accountID");
                String accountName = rs.getString("accountName");

                return new Account(accountName,email,password,accountID);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }


    //      ------  GETTERS  ------
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

    public List<Integer> getAccountIngredients(int accountID){
        connect();

        List<Integer> returnAccIngrID = new ArrayList<>();
        String sql = "SELECT ingredientID FROM account_ingredients WHERE accountID = ?";

        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1,accountID);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                returnAccIngrID.add(rs.getInt("ingredientID"));
            }
            return returnAccIngrID;

        } catch (SQLException e) {
            System.out.println("Error checking for AccountIngredients: "+e.getMessage());
        }
        return null;
    }

    public List<Integer> getAccountFavoriteDishes(int accountID){
        connect();

        List<Integer> returnFavoriteDishID = new ArrayList<>();
        String sql = "SELECT dishID FROM favorite_dishes WHERE accountID = ?";

        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1,accountID);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                returnFavoriteDishID.add(rs.getInt("dishID"));
            }
            return returnFavoriteDishID;

        } catch (SQLException e) {
            System.out.println("Error checking for Favorite Dishes: "+e.getMessage());
        }

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
