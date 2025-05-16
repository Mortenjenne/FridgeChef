package io.github.mortenjenne.fridgechef.util;

import io.github.mortenjenne.fridgechef.model.Dish;
import io.github.mortenjenne.fridgechef.model.Ingredient;

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
                PreparedStatement stm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stm.setString(1, email);
                stm.setString(2, accountName);
                stm.setString(3, password);
                int rowsUpdated = stm.executeUpdate();

                if (rowsUpdated > 0) {
                    //Account created, now gets the accountID so we can create a Fridge for the account
                    ResultSet generatedKeys = stm.getGeneratedKeys();
                    if (generatedKeys.next()){
                        int accountID = generatedKeys.getInt(1);
                        System.out.println("Generated account ID: " + accountID);
                        createFridgeForAccount(accountID);
                        System.out.println("Account and fridge created - OK");

                        return true;
                    }
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }

        return false;
    }

    private void createFridgeForAccount(int accountID) {
        String sql = "INSERT INTO fridges (accountID) VALUES (?)";

        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1,accountID);
            stm.executeUpdate();
        }catch (SQLException e){
            System.out.println(e);
        }
    }

    public void addIngredientToDatabase(Ingredient ingredient, int accountID) {
        connect();

        int ingredientID = ingredient.getId();

        String sql = "INSERT INTO account_ingredients (accountID, ingredientID) VALUES (?,?)";

        try{
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1,accountID);
            stm.setInt(2,ingredientID);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void addDishToFavorites(Dish selectedDish, int accountID) {
        connect();

        int dishID = selectedDish.getId();

        String sql = "INSERT INTO favorite_dishes (accountID, dishID) VALUES (?,?)";

        //Insert dish into favorite database
        try{
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setInt(1,accountID);
            stm.setInt(2,dishID);
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }


}
