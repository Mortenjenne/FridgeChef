package io.github.mortenjenne.fridgechef.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RecipeApiClient {

    private final String apiKeyMorten1 = "547b0ab94e70406cad3689918b802f3a";
    private final String apiKeyMorten2 = "e46029883b574497bbdc4df0b9806361";
    private final String apiKeyJesper1 = "4523e62af8d1441a889954ececef6c9a";
    private final String apiKeyDaniel1 = "d4f793953bf14d308de5180683e9a387";
    private final String apiKeyToby1 = "757341ceb29e45e78e97f4bb4d1a8776";

    private final String apiKeyInUse = apiKeyMorten2;

    private final String apiSearchByRecipe = "https://api.spoonacular.com/recipes/complexSearch";
    private final String apiSearchIngredient = "https://api.spoonacular.com/food/ingredients/search";
    private final String getApiSearchIngredientById = "https://api.spoonacular.com/food/ingredients/";
    private final String search = "?query=";
    private final String recipeMustContainIngredient = "?includeIngredients=";
    private final String cuisine = "&cuisine=";
    private final String onlyVegetarian = "&diet=vegetarian";
    private final String onlyVegan = "&diet=vegan";
    private final String intolerances = "&intolerances=";

    public String fetchRecipesByIngredientList(String ingredients, String cuisineType, boolean isVegetarian, boolean isVegan, String intolerancesType) throws Exception{
        String endpoint = apiSearchByRecipe + recipeMustContainIngredient + ingredients;

        if(isVegetarian){
            endpoint += onlyVegetarian;
        }

        if(isVegan){
            endpoint += onlyVegan;
        }

        if(!intolerancesType.isEmpty()){
            endpoint += intolerances + intolerancesType;
        }

        if(!cuisine.isEmpty()){
            endpoint += cuisine + cuisineType;
        }

        endpoint += "&number=100&apiKey=" + apiKeyInUse;
        return getResultFromApi(endpoint);
    }

    public String fetchFullRecipe(int recipeId) throws Exception{
        String endpoint = "https://api.spoonacular.com/recipes/" + recipeId + "/information?includeNutrition=false&apiKey=" + apiKeyInUse;
        return getResultFromApi(endpoint);
    }

    public String fetchRecipesByTitle(String title) throws Exception{
        String endpoint = apiSearchByRecipe + search + title + "&number=100&apiKey=" + apiKeyInUse;
        return getResultFromApi(endpoint);
    }

    public String fetchIngredientById(int ingredientId) throws Exception{
        String endpoint = getApiSearchIngredientById + ingredientId + "/information?apiKey=" + apiKeyInUse;
        return getResultFromApi(endpoint);
    }

    public String fetchIngredientByName(String name) throws Exception{
        String endpoint = apiSearchIngredient + search + name + "&apiKey=" + apiKeyInUse;
        return getResultFromApi(endpoint);
    }

    public String fetchRecipesByCuisine(String cuisine) throws Exception{
        String endpoint = apiSearchByRecipe + "?cuisine=" + cuisine + "&number=100&apiKey=" + apiKeyInUse;
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