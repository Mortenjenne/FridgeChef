package io.github.mortenjenne.fridgechef.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RecipeApiClient {
    private final String apiSearchByRecipe = "https://api.spoonacular.com/recipes/complexSearch";
    private final String apiSearchIngredient = "https://api.spoonacular.com/food/ingredients/search";
    private final String apiKey = "e46029883b574497bbdc4df0b9806361";
    private final String search = "?query=";
    private final String recipeMustContainIngredient = "?includeIngredients=";




    public String fetchRecipesByIngredientList(String ingredients) throws Exception{
        String endpoint = apiSearchByRecipe + recipeMustContainIngredient + ingredients + "&number=100&apiKey=" + apiKey;
        return getResultFromApi(endpoint);
    }

    public String fetchFullRecipe(int recipeId) throws Exception{
        String endpoint = "https://api.spoonacular.com/recipes/" + recipeId + "/information?includeNutrition=false&apiKey=" + apiKey;
        return getResultFromApi(endpoint);
    }

    public String fetchRecipesByTitle(String title) throws Exception{
        String endpoint = apiSearchByRecipe + search + title + "&number=100&apiKey=" + apiKey;
        return getResultFromApi(endpoint);
    }

    public String fetchIngredientByName(String name) throws Exception{
        String endpoint = apiSearchIngredient + search + name + "&apiKey=" + apiKey;
        return getResultFromApi(endpoint);
    }

    public String fetchRecipesByCuisine(String cuisine) throws Exception{
        String endpoint = apiSearchByRecipe + "?cuisine=" + cuisine + "&number=100&apiKey=" + apiKey;
        return getResultFromApi(endpoint);
    }

    private String getResultFromApi(String endpoint) throws Exception {
        HttpURLConnection connection = (HttpURLConnection) new URL(endpoint).openConnection();
        connection.setRequestMethod("GET");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        }
    }

}
