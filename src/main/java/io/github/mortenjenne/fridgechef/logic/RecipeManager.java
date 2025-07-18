package io.github.mortenjenne.fridgechef.logic;

import io.github.mortenjenne.fridgechef.model.Ingredient;
import io.github.mortenjenne.fridgechef.model.Recipe;
import io.github.mortenjenne.fridgechef.util.RecipeApiClient;
import io.github.mortenjenne.fridgechef.util.RecipeJsonParser;
import io.github.mortenjenne.fridgechef.model.Dish;

import java.util.List;

public class RecipeManager {
    private RecipeApiClient apiClient;
    private RecipeJsonParser jsonParser;

    public RecipeManager() {
        this.apiClient = new RecipeApiClient();
        this.jsonParser = new RecipeJsonParser();
    }

    public Recipe getFullRecipeDescription(int recipeId) throws Exception{
        String jsonResponse = apiClient.fetchFullRecipe(recipeId);
        return jsonParser.parseFullRecipeDescription(jsonResponse);
    }

    public List<Dish> getRecipesByIngredients(String ingredients, String cuisine, boolean isSearchVegetarian, boolean isSearchVegan, String intolerances) throws Exception{
        String jsonResponse = apiClient.fetchRecipesByIngredientList(ingredients,cuisine,isSearchVegetarian, isSearchVegan,intolerances);
        return jsonParser.parseRecipes(jsonResponse);
    }

    public List<Dish> getRecipesByName(String name) throws Exception {
        String jsonResponse = apiClient.fetchRecipesByTitle(name);
        return jsonParser.parseRecipes(jsonResponse);
    }

    public Dish getDishById(int recipeId) throws Exception{
        String jsonResponse = apiClient.fetchFullRecipe(recipeId);
        return jsonParser.parseDishById(jsonResponse);
    }

    public List<Dish> getRecipesByCuisine(String cuisine) throws Exception {
        String jsonResponse = apiClient.fetchRecipesByCuisine(cuisine);
        return jsonParser.parseRecipes(jsonResponse);
    }

    public List<Ingredient> getIngredient(String name) throws Exception{
        String jsonResponse = apiClient.fetchIngredientByName(name);
        return jsonParser.parseIngredients(jsonResponse);
   }

    public Ingredient getIngredientById(int recipeId) throws Exception{
        String jsonResponse = apiClient.fetchIngredientById(recipeId);
        return jsonParser.parseIngredientById(jsonResponse);
    }
}