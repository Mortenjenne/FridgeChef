package io.github.mortenjenne.fridgechef.controller;

import io.github.mortenjenne.fridgechef.logic.AppManager;
import io.github.mortenjenne.fridgechef.logic.SceneController;
import io.github.mortenjenne.fridgechef.logic.View;
import io.github.mortenjenne.fridgechef.model.Account;
import io.github.mortenjenne.fridgechef.model.Ingredient;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SearchController implements Initializable, SceneController {
    private AppManager appManager;

    @FXML private Button searchRecipeButton;
    @FXML private CheckBox isVegetarian;
    @FXML private ChoiceBox<String> chooseCuisineBox;
    @FXML private ChoiceBox<Ingredient> chooseIngredientBox1;
    @FXML private ChoiceBox<Ingredient> chooseIngredientBox2;
    @FXML private ChoiceBox<Ingredient> chooseIngredientBox3;
    @FXML private Button returnButton;

    private String[] cuisines = {"Chinese","French", "Indian", "Italian", "Japanese", "Mexican", "Thai"};
    private List<Ingredient> ingredientsInFridge;

    private String selectedCuisine;
    private String selectedIngredientOne;
    private String selectedIngredientTwo;
    private String selectedIngredientThree;
    private String searchString;


    @Override
    public void setAppManager(AppManager appManager){
        this.appManager = appManager;
        this.ingredientsInFridge = appManager.getIngredientsInFridge();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //searchRecipeButton.setOnAction(event -> appManager.switchTo(View.RESULT));
        System.out.println(ingredientsInFridge);
        returnButton.setOnAction(event -> appManager.switchTo(View.MAIN));

        chooseCuisineBox.getItems().addAll(cuisines);

        //chooseIngredientBox1.getItems().addAll(addIngredient(chooseIngredientBox1,ingredientsInFridge));
        //addIngredient(chooseIngredientBox1,ingredientsInFridge);
        chooseIngredientBox1.getItems().addAll(ingredientsInFridge);
        chooseIngredientBox2.getItems().addAll(ingredientsInFridge);
        chooseIngredientBox3.getItems().addAll(ingredientsInFridge);


        searchRecipeButton.setOnAction(event -> {
                    selectedCuisine = chooseCuisineBox.getValue();
                    selectedIngredientOne = chooseIngredientBox1.getValue().toString();
                    selectedIngredientTwo = chooseIngredientBox2.getValue().toString();
                    selectedIngredientThree = chooseIngredientBox3.getValue().toString();

                    searchString = selectedCuisine + "," + selectedIngredientOne + "," + selectedIngredientTwo + "," + selectedIngredientThree;
            System.out.println(searchString);
        });
    }

    private Ingredient addIngredient(ChoiceBox choiceBox, List ingredients){
        chooseIngredientBox1.getItems().addAll(ingredients);
        Ingredient ingredient = (Ingredient) choiceBox.getValue();
        ingredients.remove(ingredient);
        return ingredient;
    }

    private void updateChoiceBox(Ingredient ingredient){
        if(chooseIngredientBox1.getValue() == null){
            ingredientsInFridge.remove(ingredient);
        }

    }

    // Search methods from Class Diagram not added due to mismatch with "scene"

    private boolean isVegetarian(boolean isVegetarian){
        // TODO change name an code isVegetarian() body
        boolean findAppropriateName = isVegetarian;
        return findAppropriateName;
    }
}
