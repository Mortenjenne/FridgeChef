package io.github.mortenjenne.fridgechef.logic;

import io.github.mortenjenne.fridgechef.model.Account;
import io.github.mortenjenne.fridgechef.model.Dish;
import io.github.mortenjenne.fridgechef.model.Ingredient;
import io.github.mortenjenne.fridgechef.model.Recipe;
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

    public List<Recipe> getRecipeView(int recipeId){
        List<Recipe> recipes = new ArrayList<>();
        try {
            recipes = recipeManager.getImageUrl(recipeId);
        } catch (Exception e){
            System.out.println("Could not find image");
        }
        return recipes;
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
    }

    public void addIngredientToFridge(Ingredient ingredient){
        currentUser.addIngredientToFridge(ingredient);
        dbWriter.addIngredientToDatabase(ingredient, currentUser.getEmail());
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
        List<Integer> storedIngredientsID = dbReader.getAccountIngredients(currentUser.getAccountID());
        //TODO Lav en


        return storedIngredients;
    }

    public boolean login(String email, String password) {
        this.currentUser = dbReader.accountLogin(email,password);
        if(this.currentUser != null) {
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



