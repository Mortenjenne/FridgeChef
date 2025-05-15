package io.github.mortenjenne.fridgechef.util;

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

    public void addIngredientToDatabase(Ingredient ingredient, String email) {
        connect();

        int accountID = dbReader.getAccountId(email);
        System.out.println("Account email in addIngredientToDatabase: "+email);
        int ingredientID = ingredient.getId();
        String ingredientName = ingredient.getName();


        //Inserts ingredient to database if it doesnt exist already.
        String sql = "INSERT INTO ingredients (ingredientID, name) values (?, ?)";

        if(!dbReader.checkExistingIngredient(ingredientID)){
            try{
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setInt(1,ingredientID);
                stm.setString(2,ingredientName);
                stm.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }

        //Gets fridgeID, and gives error message if no fridge was found..
        int fridgeID = dbReader.getAccountFridgeID(accountID);
        if (fridgeID == 0){
            System.out.println("AccountID, in addIngredientToDatabse: "+accountID);
            System.err.println("No fridge found for this account");
            return;
        }

        //Insert ingredient into fridge_ingredients database
        if(dbReader.checkExistingIngredient(ingredientID)){
            //double defaultQuantity = 1.0;

            sql = "INSERT INTO fridge_ingredients (fridgeID, ingredientID) VALUES (?,?)";

            /*USE THIS IF QUANTITY IS BEING USED
            sql = "INSERT INTO fridge_ingredients (fridgeID, ingredientsID, quantity) VALUES (?,?,?) "
                + "ON DUPLICATE KEY UPDATE quantity = quantity + ?";

             */

            try{
                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setInt(1,fridgeID);
                stm.setInt(2,ingredientID);
                stm.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }


    }
}
