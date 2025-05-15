package io.github.mortenjenne.fridgechef.controller;

import io.github.mortenjenne.fridgechef.logic.AppManager;
import io.github.mortenjenne.fridgechef.logic.SceneController;
import io.github.mortenjenne.fridgechef.logic.View;
import io.github.mortenjenne.fridgechef.model.AnalyzedInstruction;
import io.github.mortenjenne.fridgechef.model.ExtendedIngredient;
import io.github.mortenjenne.fridgechef.model.InstructionStep;
import io.github.mortenjenne.fridgechef.model.Recipe;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RecipeController implements Initializable, SceneController {
    private AppManager appManager;

    @FXML private Button returnButton, addToFavoriteButton;
    @FXML private Label recipeNameLabel;
    @FXML private Label timeLabel;
    @FXML private Label servingsLabel;
    @FXML private Label ingredientLabel;
    @FXML private ListView ingredientView;

    private Recipe recipe;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnButton.setOnAction(event -> appManager.switchTo(View.RESULT));
        addToFavoriteButton.setOnAction(event -> appManager.addToFavoriteDishes(appManager.getSelectedDish()));

    }

    @Override
    public void setAppManager(AppManager appManager) {
        this.appManager = appManager;
        recipe = appManager.getFullRecipeDescription(appManager.getSelectedDishId());
        recipeNameLabel.setText(recipe.getTitle());
        recipeWidget.setImage(new Image(recipe.getImage()));
        System.out.println("Navn: " + recipe.getTitle());
        System.out.println("Tid: " + recipe.getReadyInMinutes() + " min");
        System.out.println("Serveringer: " + recipe.getServings());
        System.out.println("Instruktioner: " + recipe.getInstructions());
        System.out.println("Ingredienser:");

        for (ExtendedIngredient ing : recipe.getExtendedIngredients()) {
            System.out.println(" - " + ing);
        }

        for(AnalyzedInstruction ing: recipe.getAnalyzedInstructions()){
            for(InstructionStep steps: ing.getSteps()){
                System.out.println(steps.getNumber() + ": " + steps.getStep());
            }

        }

    }

}


