package io.github.mortenjenne.fridgechef.util;

import io.github.mortenjenne.fridgechef.model.Account;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseReader extends DatabaseConnector{

    public Account accountLogin (String email, String password){
        connect();

        String sql = "SELECT * FROM accounts WHERE email = ? AND password = ?";

        try {
            try (PreparedStatement stm = conn.prepareStatement(sql)) {
                stm.setString(1, email);
                stm.setString(2, password);

                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        int accountID = rs.getInt("accountID");
                        String accountName = rs.getString("accountName");

                        return new Account(accountName,email,password,accountID);
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }

    //      ------  GETTERS  ------
    public int getAccountId(String email) {
        connect();
        String sql = "SELECT accountID FROM accounts WHERE email = ?";
        int value = 0;
        try{
            try (PreparedStatement stm = conn.prepareStatement(sql)){
                stm.setString(1,email);
                try (ResultSet rs = stm.executeQuery()){
                    if (rs.next()) {
                        System.out.println("getAccountID has next");
                        value = rs.getInt("accountID"); //Returns accountID if account email exists
                    }
                }
            }
        } catch (SQLException e){
            System.out.println("Error checking for accountID: "+e.getMessage());
        } finally {
            closeConnection();
        }
        return value;
    }

    public String getAccountName(String email) {
        connect();

        String sql = "SELECT accountName FROM accounts WHERE email = ?";

        try{
            try (PreparedStatement stm = conn.prepareStatement(sql)){
                stm.setString(1,email);
                try (ResultSet rs = stm.executeQuery()){
                    if (rs.next()) {
                        return rs.getString("accountName"); //Returns accountName if account email exists
                    }
                }
            }

        } catch (SQLException e){
            System.out.println("Error checking for accountname: "+e.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }

    public List<Integer> getAccountIngredients(int accountID){
        connect();

        List<Integer> returnAccIngrID = new ArrayList<>();
        String sql = "SELECT ingredientID FROM account_ingredients WHERE accountID = ?";

        try {
            try (PreparedStatement stm = conn.prepareStatement(sql)){
                stm.setInt(1,accountID);
                try (ResultSet rs = stm.executeQuery()){
                    while(rs.next()){
                        returnAccIngrID.add(rs.getInt("ingredientID"));
                    }
                }
            }
            return returnAccIngrID;

        } catch (SQLException e) {
            System.out.println("Error checking for AccountIngredients: "+e.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }

    public List<Integer> getAccountFavoriteDishes(int accountID){
        connect();

        List<Integer> returnFavoriteDishID = new ArrayList<>();
        String sql = "SELECT dishID FROM favorite_dishes WHERE accountID = ?";

        try {
            try (PreparedStatement stm = conn.prepareStatement(sql)){
                stm.setInt(1,accountID);
                try (ResultSet rs = stm.executeQuery()){
                    while(rs.next()){
                        returnFavoriteDishID.add(rs.getInt("dishID"));
                    }
                }
            }
            return returnFavoriteDishID;

        } catch (SQLException e) {
            System.out.println("Error checking for Favorite Dishes: "+e.getMessage());
        } finally {
            closeConnection();
        }

        return null;
    }


    //      ------  CHECKERS  ------
    public boolean checkExistingAccount (String email){
        connect();
        String sql = "SELECT email FROM accounts WHERE email = ?";

        try{
            try (PreparedStatement stm = conn.prepareStatement(sql)){
                stm.setString(1,email);
                try (ResultSet rs = stm.executeQuery()){
                    if (rs.next()) {
                        return true; //Returns true if account exist
                    }
                }
            }

        } catch (SQLException e){
            System.out.println("Error checking for existing accounts: "+e.getMessage());
        } finally {
            closeConnection();
        }
        return false; //Returns false if account does NOT exist
    }

    public boolean checkExistingIngredient(int ingredientID) {
        connect();

        String sql = "SELECT ingredientID FROM ingredients WHERE ingredientID = ?";
        try{
            try (PreparedStatement stm = conn.prepareStatement(sql)){
                stm.setInt(1,ingredientID);
                try (ResultSet rs = stm.executeQuery()){
                    if (rs.next()) {
                        return true; //Returns true if ingredient exist
                    }
                }
            }

        } catch (SQLException e){
            System.out.println("Error checking for existing ingredients: "+e.getMessage());
        } finally {
            closeConnection();
        }
        return false; //Returns false if ingredient does NOT exist
    }
}