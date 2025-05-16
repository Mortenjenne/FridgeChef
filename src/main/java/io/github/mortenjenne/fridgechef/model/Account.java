package io.github.mortenjenne.fridgechef.model;

import java.util.ArrayList;
import java.util.List;

public class Account {

    // Accountinfo
    private int accountID;
    private String userName;
    private String email;
    private String password;

    // Dishes
    private List<Dish> favoriteDishes;

    // Fridge
    private Ingredient ingredient;
    private int amount;
    private List<Ingredient> ingredientsInFridge;

    public Account(){
        this.favoriteDishes = new ArrayList<>();
        this.ingredientsInFridge = new ArrayList<>();
    }


    public Account(String userName, String email, String password, int accountID){
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.accountID = accountID;
        this.favoriteDishes = new ArrayList<>();
        this.ingredientsInFridge = new ArrayList<>();
    }

    // Accountinfo
    public String getUserName(){
        return this.userName;
    }

    public int getAccountID(){
        return this.accountID;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Dishes
    public List<Dish> getFavoriteDishes(){
        return this.favoriteDishes;
    }

    public void addToFavorites(Dish dish){
        this.favoriteDishes.add(dish);
    }

    public void removeFromFavorites(Dish dish){
        this.favoriteDishes.remove(dish);
    }


    // Fridge
    public void addIngredientToFridge(Ingredient ingredient){
        this.ingredientsInFridge.add(ingredient);
    }

    public void removeIngredientFromFridge(Ingredient ingredient){
        this.ingredientsInFridge.remove(ingredient);
    }

    public boolean useIngredient(String name){
        return false;
    }

    public List<Ingredient> getIngredientsInFridge(){
        return this.ingredientsInFridge;
    }

}
