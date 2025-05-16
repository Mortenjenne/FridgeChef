package io.github.mortenjenne.fridgechef.controller;

import io.github.mortenjenne.fridgechef.logic.AppManager;
import io.github.mortenjenne.fridgechef.logic.SceneController;
import io.github.mortenjenne.fridgechef.logic.View;
import io.github.mortenjenne.fridgechef.model.AnalyzedInstruction;
import io.github.mortenjenne.fridgechef.model.ExtendedIngredient;
import io.github.mortenjenne.fridgechef.model.InstructionStep;
import io.github.mortenjenne.fridgechef.model.Recipe;
import javafx.beans.Observable;
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
    private AppManager appManager;

    @FXML private Button returnButton, addToFavoriteButton;
    @FXML private Label recipeNameLabel;
    @FXML private Label timeLabel;
    @FXML private Label servingsLabel;
    @FXML private Label ingredientLabel;
    @FXML private ListView ingredientView;
    @FXML private TextArea textArea;
    @FXML private ImageView recipeImage;
    private String instructions = "";

    private Recipe recipe;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnButton.setOnAction(event -> returnButtonOptions());
        addToFavoriteButton.setOnAction(event -> appManager.addToFavoriteDishes(appManager.getSelectedDish()));

    }

    @Override
    public void setAppManager(AppManager appManager) {
        this.appManager = appManager;
        recipe = appManager.getFullRecipeDescription(appManager.getSelectedDishId());
        if(recipe != null){
            recipeNameLabel.setText(recipe.getTitle());
            timeLabel.setText("Cooking time: " + String.valueOf(recipe.getReadyInMinutes()) + " mins");
            servingsLabel.setText("Serves: " + String.valueOf(recipe.getServings()) + " people");
            ingredientLabel.setText("Ingredients:");
            recipeImage.setImage(new Image(recipe.getImage()));
            setListView();


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
                    instructions += steps.getNumber() + ": " + steps.getStep() + "\n";
                }
            }
            textArea.setText(instructions);

        } else {
            System.out.println("Recipe not found/loaded");
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

    private void returnButtonOptions(){
        if(appManager.getShowRecipeFromFavorites()) {
            returnButton.setOnAction(event -> appManager.switchTo(View.FAVORITES));
            appManager.setShowRecipeFromFavorites(false);
        } else{
        returnButton.setOnAction(event -> appManager.switchTo(View.RESULT));
        }
    }
}


