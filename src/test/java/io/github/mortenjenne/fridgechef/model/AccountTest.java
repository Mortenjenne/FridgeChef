package io.github.mortenjenne.fridgechef.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    private Account account;
    private Ingredient ingredient;
    private Dish dish;

    @BeforeEach
    public void setUp(){
        account = new Account();
        ingredient = new Ingredient();
        ingredient.setName("onion");
        dish = new Dish();
        dish.setTitle("Butter Chicken");
    }

    @Test
    void testAddToFavorites() {
        assertTrue(account.getFavoriteDishes().isEmpty());

        account.addToFavorites(dish);
        assertEquals(1,account.getFavoriteDishes().size());
    }

    @Test
    void testDishInListEqualsNameAdded(){
        account.addToFavorites(dish);
        assertEquals("Butter Chicken",account.getFavoriteDishes().get(0).getTitle());
    }

    @Test
    void testRemoveFromFavorites() {
        account.addToFavorites(dish);
        account.removeFromFavorites(dish);
        assertEquals(0,account.getFavoriteDishes().size());
    }

    @Test
    void testAddIngredientToFridge() {
        assertTrue(account.getIngredientsInFridge().isEmpty());

        account.addIngredientToFridge(ingredient);
        assertEquals(1,account.getIngredientsInFridge().size());
    }

    @Test
    void testRemoveIngredientFromFridge() {
        account.addIngredientToFridge(ingredient);
        account.removeIngredientFromFridge(ingredient);
        assertEquals(0,account.getIngredientsInFridge().size());
    }
}