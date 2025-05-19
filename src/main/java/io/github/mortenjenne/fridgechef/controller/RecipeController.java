package io.github.mortenjenne.fridgechef.controller;

import io.github.mortenjenne.fridgechef.logic.AppManager;
import io.github.mortenjenne.fridgechef.logic.SceneController;
import io.github.mortenjenne.fridgechef.logic.View;
import io.github.mortenjenne.fridgechef.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RecipeController implements Initializable, SceneController {
    @FXML private Button returnButton, addToFavoriteButton;
    @FXML private Label recipeNameLabel;
    @FXML private Label timeLabel;
    @FXML private Label servingsLabel;
    @FXML private Label ingredientLabel;
    @FXML private Label isVeganLabel;
    @FXML private Label favoriteConfirmLabel;
    @FXML private ListView ingredientView;
    @FXML private TextArea textArea;
    @FXML private ImageView recipeImage;

    private AppManager appManager;
    private String instructions = "";
    private Recipe recipe;

    @Override
    public void setAppManager(AppManager appManager) {
        this.appManager = appManager;
        this.recipe = appManager.getFullRecipeDescription(appManager.getSelectedDishId());
        loadRecipe();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        favoriteConfirmLabel.setText("");

        returnButton.setOnAction(event -> {
            if (appManager.getShowRecipeFromFavorites()) {
                appManager.setShowRecipeFromFavorites(false);
                appManager.switchTo(View.FAVORITES);
            } else {
                appManager.switchTo(View.RESULT);
            }
        });

        addToFavoriteButton.setOnAction(event -> addToFavorites());
    }

    private void addToFavorites() {
        Dish dish = appManager.getSelectedDish();
        boolean dishNotFound = true;

        if (dish == null) {
            return;
        }
        for (Dish d : appManager.getFavoriteDishesList()) {
            if (dish.getId() == d.getId()) {
                dishNotFound = false;
            }
        }
        if (dishNotFound) {
            appManager.addToFavoriteDishes(dish);
            String recipeAdded = "Recipe added!";
            favoriteConfirmLabel.setText(recipeAdded);
        } else {
            appManager.removeFromFavoriteDishes(dish);
            String recipeRemoved = "Recipe removed!";
            favoriteConfirmLabel.setText(recipeRemoved);
        }
    }

    private void setListView(){
        List<String> ingredients = new ArrayList<>();
        for(ExtendedIngredient ingredient: recipe.getExtendedIngredients()){
            ingredients.add(ingredient.toString());
        }
        ObservableList<String> observableList = FXCollections.observableArrayList(ingredients);
        ingredientView.setItems(observableList);
    }

    private void loadRecipe(){
        if(recipe != null){
            recipeNameLabel.setText(recipe.getTitle());
            timeLabel.setText("Cooking time: " + String.valueOf(recipe.getReadyInMinutes()) + " mins");
            servingsLabel.setText("Serves: " + String.valueOf(recipe.getServings()) + " people");
            ingredientLabel.setText("Ingredients:");
            recipeImage.setImage(new Image(recipe.getImage()));

            if(recipe.isVegan()){
                isVeganLabel.setText("Vegan");
            } else if(recipe.IsVegetarian()){
                isVeganLabel.setText("Vegetarian");
            } else {
                isVeganLabel.setText("Not vegetarian");
            }
            setListView();

            for(AnalyzedInstruction ing: recipe.getAnalyzedInstructions()){
                for(InstructionStep steps: ing.getSteps()){
                    instructions += steps.getNumber() + ": " + steps.getStep() + "\n";
                }
            }
            textArea.setText(instructions);

        } else {
            recipeNameLabel.setText("⚠ Recipe not found");
            timeLabel.setText("");
            servingsLabel.setText("");
            ingredientLabel.setText("");
            textArea.setText("Something went wrong with this recipe. Please try again later.");
            favoriteConfirmLabel.setText("");
            recipeImage.setImage(null);
        }
    }
}