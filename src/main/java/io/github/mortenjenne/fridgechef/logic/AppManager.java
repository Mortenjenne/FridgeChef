package io.github.mortenjenne.fridgechef.logic;

import io.github.mortenjenne.fridgechef.model.Account;
import io.github.mortenjenne.fridgechef.model.Dish;
import io.github.mortenjenne.fridgechef.model.Ingredient;
import io.github.mortenjenne.fridgechef.util.DatabaseReader;
import io.github.mortenjenne.fridgechef.util.DatabaseWriter;

import java.util.ArrayList;
import java.util.List;

public class AppManager {
private RecipeManager recipeManager;
private Account currentUser;
private SceneNavigator sceneNavigator;
private DatabaseReader dbReader = new DatabaseReader();
private DatabaseWriter dbWriter = new DatabaseWriter();

private String searchQuery;
private Dish selectedDish;

    public AppManager(SceneNavigator sceneNavigator, RecipeManager recipeManager, Account currentUser) {
        this.recipeManager = recipeManager;
        this.sceneNavigator = sceneNavigator;
        this.currentUser = currentUser;
    }

    public void setSelectedRecipe(Dish selectedDish){this.selectedDish = selectedDish;}

    public int getSelectedDish(){return this.selectedDish.getId();}

    public void setSearchQuery(String query){
        this.searchQuery = query;
    }

    public String getSearchQuery(){
        return this.searchQuery;
    }

    public Account getCurrentUser(){
        return this.currentUser;
    }

    public void addIngredientToFridge(Ingredient ingredient){
        currentUser.addIngredientToFridge(ingredient);
    }

    public void removeIngredientFromFridge(Ingredient ingredient){
        currentUser.removeIngredientFromFridge(ingredient);
    }

    public List<Ingredient> getIngredientsInFridge(){
        return currentUser.getIngredientsInFridge();
    }

    public void switchTo(View view) {
        sceneNavigator.switchTo(view);
    }



    public List<Ingredient> loadFridgeIngredients(){
        List<Ingredient> storedIngredients = new ArrayList<>();
        //TODO Vi skal kunne hente en brugers ingredienser fra databasen og dermed få det vist i køle og opskriftsøgning
        //storedIngredients = Dbreader.getUserIngredients()
        return storedIngredients;
    }

    public boolean login(String email, String password) {
        if (dbReader.accountLogin(email, password)) {
            return true;
        }
        return false;
    }

    public void createAccount(String accountName, String email, String password) {
        this.currentUser = new Account(accountName, email, password);
        //dbWriter.createAccount(accountName,email,password);
    }

    public boolean isEmailValid(String email) {
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            return false;
        }
        return true;
    }

    public boolean isUserNameValid(String userName) {
        return userName.trim().length() >= 2;
    }


    public boolean isValidPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            return false;
        }
        if (password.length() < 6) {
            return false;
        }
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }
        if (!password.matches(".*[a-z].*")) {
            return false;
        }
        if (!password.matches(".*\\d.*")) {
            return false;
        }
        return true;
    }

    public boolean isEmailInSystem(String email) {
        return dbReader.checkExistingAccount(email);
    }

    public boolean isPasswordIndentical(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    public List<Ingredient> searchIngredients (String name){
        List<Ingredient> ingredients = new ArrayList<>();
        try {
            ingredients = recipeManager.getIngredient(name);
        } catch (Exception e){
            System.out.println("Error loading ingredient search");
        }
        return ingredients;
    }

    public List<Dish> searchRecipesByIngredientList (String ingredients){
        List<Dish> dishes = new ArrayList<>();
        try {
            dishes = recipeManager.getRecipesByIngredients(ingredients);
        } catch (Exception e){
            System.out.println("Error loading ingredient search");
        }
        return dishes;
    }

}

    /*public String validatePasswordRequirements(String password) {
        if (password == null || password.trim().isEmpty()) {
            return "Password cannot be empty.";
        }
        if (password.length() < 6) {
            return "Password must be at least 6 characters long.";
        }
        if (!password.matches(".*[A-Z].*")) {
            return "Password must contain at least one uppercase letter.";
        }
        if (!password.matches(".*[a-z].*")) {
            return "Password must contain at least one lowercase letter.";
        }
        if (!password.matches(".*\\d.*")) {
            return "Password must contain at least one digit.";
        }
        return ""; // Return an empty string as a success indicator
    }
    */


