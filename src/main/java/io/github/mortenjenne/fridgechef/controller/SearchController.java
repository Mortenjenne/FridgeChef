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

    @FXML
    private Button searchRecipeButton;
    @FXML
    private CheckBox isVegetarian;
    @FXML
    private ChoiceBox<String> chooseCuisineBox;
    @FXML
    private ChoiceBox<Ingredient> chooseIngredientBox1;
    @FXML
    private ChoiceBox<Ingredient> chooseIngredientBox2;
    @FXML
    private ChoiceBox<Ingredient> chooseIngredientBox3;
    @FXML
    private Button returnButton;

    private String[] cuisines = {"Chinese", "French", "Indian", "Italian", "Japanese", "Mexican", "Thai"};
    private List<Ingredient> ingredientsInFridge;

    private String selectedCuisine;
    private String selectedIngredientOne;
    private String selectedIngredientTwo;
    private String selectedIngredientThree;
    private String searchString;


    @Override
    public void setAppManager(AppManager appManager) {
        this.appManager = appManager;
        fillChoiceBoxes();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // searchRecipeButton.setOnAction(event -> appManager.switchTo(View.RESULT));
        returnButton.setOnAction(event -> appManager.switchTo(View.MAIN));


        searchRecipeButton.setOnAction(event -> {
            selectedCuisine = chooseCuisineBox.getValue();
            selectedIngredientOne = chooseIngredientBox1.getValue().toString();
            selectedIngredientTwo = chooseIngredientBox2.getValue().toString();
            selectedIngredientThree = chooseIngredientBox3.getValue().toString();

            searchString = selectedIngredientOne + "," + selectedIngredientTwo + "," + selectedIngredientThree;

            appManager.setSearchQuery(searchString);
            appManager.switchTo(View.RESULT);

            System.out.println(searchString);
        });
    }

    private Ingredient addIngredient(ChoiceBox choiceBox, List ingredients) {
        chooseIngredientBox1.getItems().addAll(ingredients);
        Ingredient ingredient = (Ingredient) choiceBox.getValue();
        ingredients.remove(ingredient);
        return ingredient;
    }

    // Search methods from Class Diagram not added due to mismatch with "scene"

    private boolean isVegetarian(boolean isVegetarian) {
        // TODO change name an code isVegetarian() body
        boolean findAppropriateName = isVegetarian;
        return findAppropriateName;
    }

    public void setIngredientsInFridge(List<Ingredient> ingredients) {
        this.ingredientsInFridge = ingredients;
    }

    private void fillChoiceBoxes() {
        this.ingredientsInFridge = appManager.getIngredientsInFridge();
        chooseCuisineBox.getItems().addAll(cuisines);
        chooseIngredientBox1.getItems().addAll(ingredientsInFridge);
        chooseIngredientBox2.getItems().addAll(ingredientsInFridge);
        chooseIngredientBox3.getItems().addAll(ingredientsInFridge);
    }


    /*
    private void addlistener(ChoiceBox<Ingredient> box){
        box.getSelectionModel().selectedItemProperty().addListener((obs,oldVal,newValue) ->) {
        updateChoiceBoxes();

    }
    private void updateChoiceBoxes(){
        Ingredient selectedOne = chooseIngredientBox1.getValue();
        Ingredient selectedTwo = chooseIngredientBox2.getValue();
        Ingredient selectedThree = chooseIngredientBox3.getValue();

        List<Ingredient> selected = new ArrayList<>();
        if (selectedOne != null)selected.add(selectedOne);
        if (selectedTwo != null)selected.add(selectedTwo);
        if (selectedThree != null)selected.add(selectedThree);

    }

     */
}
