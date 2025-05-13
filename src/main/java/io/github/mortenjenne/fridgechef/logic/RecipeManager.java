package io.github.mortenjenne.fridgechef.logic;

import io.github.mortenjenne.fridgechef.model.Ingredient;
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

    public List<Dish> getRecipesByTitle(String ingredients) throws Exception {
        String jsonResponse = apiClient.fetchRecipesByTitle(ingredients);
        return jsonParser.parseRecipes(jsonResponse);
    }

    public List<Dish> getRecipesByCuisine(String cuisine) throws Exception {
        String jsonResponse = apiClient.fetchRecipesByCuisine(cuisine);
        return jsonParser.parseRecipes(jsonResponse);
    }

    public List<Ingredient> getIngredient(String name) throws Exception{
        String jsonResponse = apiClient.fetchIngredientByName(name);
        return jsonParser.parseIngredients(jsonResponse);
   }
}