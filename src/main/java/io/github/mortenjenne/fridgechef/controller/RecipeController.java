package io.github.mortenjenne.fridgechef.controller;

import io.github.mortenjenne.fridgechef.logic.AppManager;
import io.github.mortenjenne.fridgechef.logic.SceneController;
import io.github.mortenjenne.fridgechef.logic.View;
import io.github.mortenjenne.fridgechef.model.Recipe;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RecipeController implements Initializable, SceneController {
    private AppManager appManager;

    @FXML private Label recipeNameLabel;
    @FXML private ImageView recipeWidget;
    @FXML private Button returnButton, addToFavoriteButton;

    private List<Recipe> recipe;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnButton.setOnAction(event -> returnButtonOptions());
        addToFavoriteButton.setOnAction(event -> appManager.addToFavoriteDishes(appManager.getSelectedDish()));

    }

    @Override
    public void setAppManager(AppManager appManager) {
        this.appManager = appManager;
        recipe = appManager.getRecipeView(appManager.getSelectedDishId());
        recipeNameLabel.setText(appManager.getSelectedDishTitle());
        //recipeWidget.setImage(new Image(recipe.get(0).getUrl()));
    }

    private void returnButtonOptions(){
        if(appManager.getShowRecipeFromFavorites()) {
            returnButton.setOnAction(event -> appManager.switchTo(View.FAVORITES));
            System.out.println(appManager.getShowRecipeFromFavorites());
            appManager.setShowRecipeFromFavorites(false);
        } else{
        returnButton.setOnAction(event -> appManager.switchTo(View.RESULT));
        }
    }
}


