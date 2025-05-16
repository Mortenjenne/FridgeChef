package io.github.mortenjenne.fridgechef.logic;

import io.github.mortenjenne.fridgechef.model.Account;
import io.github.mortenjenne.fridgechef.model.Dish;
import io.github.mortenjenne.fridgechef.model.Ingredient;
import io.github.mortenjenne.fridgechef.model.Recipe;
import io.github.mortenjenne.fridgechef.util.DatabaseConnector;
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
private boolean showRecipeFromFavorites = false;

    public AppManager(SceneNavigator sceneNavigator, RecipeManager recipeManager, Account currentUser) {
        this.recipeManager = recipeManager;
        this.sceneNavigator = sceneNavigator;
        this.currentUser = currentUser;
    }

    public void setSelectedRecipe(Dish selectedDish){this.selectedDish = selectedDish;}

    public Dish getSelectedDish(){return this.selectedDish;}

    public int getSelectedDishId(){return this.selectedDish.getId();}

    public String getSelectedDishTitle(){return this.selectedDish.getTitle();}

    public void setSearchQuery(String query){
        this.searchQuery = query;
    }

    public String getSearchQuery(){
        return this.searchQuery;
    }

    public Account getCurrentUser(){
        return this.currentUser;
    }

    public void addToFavoriteDishes(Dish selectedDish) {
        currentUser.addToFavorites(selectedDish);
        dbWriter.addDishToFavorites(selectedDish,currentUser.getAccountID());
    }

    public void addIngredientToFridge(Ingredient ingredient){
        currentUser.addIngredientToFridge(ingredient);
        dbWriter.addIngredientToDatabase(ingredient, currentUser.getAccountID());
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

    public List<Dish> loadFavoriteDishes(){
        List<Dish> storedFavoriteDishes = new ArrayList<>();
        List<Integer> storedFavoriteDishesID = dbReader.getAccountFavoriteDishes(currentUser.getAccountID());
        //TODO Lav en metode til at søge på Integer listen storedFavoriteDishesID

        return storedFavoriteDishes;
    }

    public List<Ingredient> loadFridgeIngredients(){
        List<Ingredient> storedIngredients = new ArrayList<>();
        List<Integer> storedIngredientsID = dbReader.getAccountIngredients(currentUser.getAccountID());
        //TODO Lav en metode til at søge på Integer listen storedIngredientsID

        return storedIngredients;
    }

    public Recipe getFullRecipeDescription(int recipeId){
        Recipe recipe = null;
        try {
            recipe = recipeManager.getFullRecipeDescription(recipeId);
        } catch (Exception e){
            System.out.println("Error loading full recipe information");
        }
        return recipe;
    }

    public List<Ingredient> searchIngredients (String name){
        List<Ingredient> ingredients = new ArrayList<>();
        try {
            ingredients = recipeManager.getIngredient(name);
        } catch (Exception e){
            System.out.println("Error loading ingredient search");
            e.getMessage();
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

    public void setShowRecipeFromFavorites(boolean showRecipeFromFavorites) {
        this.showRecipeFromFavorites = showRecipeFromFavorites;
    }

    public boolean getShowRecipeFromFavorites(){
        return this.showRecipeFromFavorites;
    }

    //  ------  ACCOUNT  ------
    public boolean login(String email, String password) {
        this.currentUser = dbReader.accountLogin(email,password);
        if(this.currentUser != null) {
            //TODO Tilføj til metoden her: Load brugerens køleskab fra databasen ind i Listen på Account objektet
            return true;
        }
        return false;
    }

    public void createAccount(String accountName, String email, String password) {
        if(!dbReader.checkExistingAccount(email)) {
            dbWriter.createAccount(email, accountName, password);
        }
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
}



