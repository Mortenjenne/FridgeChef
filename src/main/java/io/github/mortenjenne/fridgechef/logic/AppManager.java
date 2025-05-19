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
private Dish selectedDish;
private DatabaseReader dbReader = new DatabaseReader();
private DatabaseWriter dbWriter = new DatabaseWriter();
private String searchQuery;
private String cuisineQuery;
private String intolerances;
private boolean isSearchOnlyVegan;
private boolean isSearchOnlyVegetarian;
private boolean showRecipeFromFavorites = false;
private boolean accountCreated = false;

    public AppManager(SceneNavigator sceneNavigator, RecipeManager recipeManager, Account currentUser) {
        this.recipeManager = recipeManager;
        this.sceneNavigator = sceneNavigator;
        this.currentUser = currentUser;
    }

    public void setIsSearchVegan(boolean isSearchOnlyVegan){
        this.isSearchOnlyVegan = isSearchOnlyVegan;
    }

    public void setIntolerances(String intolerances){
        this.intolerances = intolerances;
    }

    public String getIntolerances(){
        return this.intolerances;
    }

    public boolean isSearchOnlyVegan(){
        return  this.isSearchOnlyVegan;
    }

    public void setCuisineQuery(String cuisine){
        this.cuisineQuery = cuisine;
    }

    public void setIsSearchVegetarian(boolean isVegetarian){
        this.isSearchOnlyVegetarian = isVegetarian;
    }

    public String getCuisineQuery(){
        return  this.cuisineQuery;
    }

    public boolean getIsSearchOnlyVegetarian(){
        return this.isSearchOnlyVegetarian;
    }

    public void setSelectedRecipe(Dish selectedDish){this.selectedDish = selectedDish;}

    public void setSearchQuery(String query){
        this.searchQuery = query;
    }

    public Dish getSelectedDish(){return this.selectedDish;}

    public int getSelectedDishId(){return this.selectedDish.getId();}

    public String getSearchQuery(){
        return this.searchQuery;
    }

    public Account getCurrentUser(){
        return this.currentUser;
    }

    public List<Ingredient> getIngredientsInFridge(){
        return currentUser.getIngredientsInFridge();
    }

    public void addToFavoriteDishes(Dish selectedDish) {
        currentUser.addToFavorites(selectedDish);
        dbWriter.addDishToFavorites(selectedDish,currentUser.getAccountID());
    }

    public void removeFromFavoriteDishes(Dish selectedDish) {
        currentUser.removeFromFavorites(selectedDish);
        dbWriter.removeDishFromFavorites(selectedDish,currentUser.getAccountID());
    }

    public void switchTo(View view) {
        sceneNavigator.switchTo(view);
    }

    public Recipe getFullRecipeDescription(int recipeId){
        Recipe recipe = null;
        try {
            recipe = recipeManager.getFullRecipeDescription(recipeId);
        } catch (Exception e){
            System.out.println("Error loading full recipe information" + e.getMessage());
        }
        return recipe;
    }

    //  ------  SEARCH  ------

    public List<Ingredient> searchIngredients (String name){
        List<Ingredient> ingredients = new ArrayList<>();
        try {
            ingredients = recipeManager.getIngredient(name);
        } catch (Exception e){
            System.out.println("Error loading ingredient search" + e.getMessage());
            e.getMessage();
        }
        return ingredients;
    }

    public List<Dish> searchRecipesByIngredientList (String ingredients, String cuisine, boolean isSearchOnlyVegetarian, boolean isSearchOnlyVegan, String intolerances){
        List<Dish> dishes = new ArrayList<>();
        try {
            dishes = recipeManager.getRecipesByIngredients(ingredients,cuisine,isSearchOnlyVegetarian,isSearchOnlyVegan,intolerances);
        } catch (Exception e){
            System.out.println("Error loading ingredient search" + e.getMessage());
        }
        return dishes;
    }

    //  ------  FRIDGE  ------
    public void addIngredientToFridge(Ingredient ingredient){
        currentUser.addIngredientToFridge(ingredient);
        dbWriter.addIngredientToDatabase(ingredient, currentUser.getAccountID());
    }

    public void removeIngredientFromFridge(Ingredient ingredient){
        currentUser.removeIngredientFromFridge(ingredient);
        dbWriter.removeIngredientFromDatabase(ingredient, currentUser.getAccountID());
    }

    public void loadFridgeIngredients(){
        Ingredient ingredient = null;
        List<Integer> storedIngredientsID = dbReader.getAccountIngredients(currentUser.getAccountID());

        for(Integer recipeId: storedIngredientsID){
            try {
                ingredient = recipeManager.getIngredientById(recipeId);
                this.currentUser.addIngredientToFridge(ingredient);

            } catch (Exception e){
                System.out.println("Error retrieving account ingredients" + e.getMessage());
            }
        }
    }

    //  ------  FAVORITE  ------
    public List<Dish> getFavoriteDishesList(){
        return currentUser.getFavoriteDishes();
    }

    public void setShowRecipeFromFavorites(boolean showRecipeFromFavorites) {
        this.showRecipeFromFavorites = showRecipeFromFavorites;
    }

    public boolean getShowRecipeFromFavorites(){
        return this.showRecipeFromFavorites;
    }

    public void loadFavoriteDishes(){

        List<Integer> storedFavoriteDishesID = dbReader.getAccountFavoriteDishes(currentUser.getAccountID());

        for(Integer dishId: storedFavoriteDishesID){
            try {
                Dish dish = recipeManager.getDishById(dishId);
                this.currentUser.addToFavorites(dish);
            } catch (Exception e){
                System.out.println("Error loading account favorite dishes" + e.getMessage());
            }
        }
    }

    //  ------  ACCOUNT  ------
    public boolean login(String email, String password) {
        this.currentUser = dbReader.accountLogin(email,password);
        if(this.currentUser != null) {
            loadFavoriteDishes();
            loadFridgeIngredients();
            return true;
        }
        return false;
    }

    public void createAccount(String accountName, String email, String password) {
        if(!dbReader.checkExistingAccount(email)) {
            dbWriter.createAccount(email, accountName, password);
        }
    }

    public boolean isEmailInSystem(String email) {
        return dbReader.checkExistingAccount(email);
    }

    public void setAccountCreated(boolean b) {
        this.accountCreated = b;
    }

    public boolean getAccountCreated(){
        return this.accountCreated;
    }
}