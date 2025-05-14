package io.github.mortenjenne.fridgechef.util;

import com.google.gson.Gson;
import io.github.mortenjenne.fridgechef.model.Dish;
import io.github.mortenjenne.fridgechef.model.Ingredient;
import io.github.mortenjenne.fridgechef.model.Recipe;

import java.util.List;

public class RecipeJsonParser {
    private Gson gson = new Gson();

    public List<Dish> parseRecipes(String jsonResponse) {
        RecipeResponse response = gson.fromJson(jsonResponse, RecipeResponse.class);
        return response.getResults();
    }

    public List<Ingredient> parseIngredients(String jsonResponse) {
        IngredientResponse response = gson.fromJson(jsonResponse, IngredientResponse.class);
        return response.getResults();
    }

    public List<Recipe> parseRecipeImage(String jsonRespone){

        TestResponse test = gson.fromJson(jsonRespone, TestResponse.class);
        return test.getResults();
    }
}
