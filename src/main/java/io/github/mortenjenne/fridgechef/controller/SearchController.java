package io.github.mortenjenne.fridgechef.controller;

import io.github.mortenjenne.fridgechef.logic.AppManager;
import io.github.mortenjenne.fridgechef.logic.SceneController;
import io.github.mortenjenne.fridgechef.logic.View;
import io.github.mortenjenne.fridgechef.model.Ingredient;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;


import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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

    private final String[] cuisines = {"Chinese", "French", "Indian", "Italian", "Japanese", "Mexican", "Thai"};
    private List<Ingredient> ingredientsInFridge;

    private boolean isUpdatingChoiceBoxes = false;

    @Override
    public void setAppManager(AppManager appManager) {
        this.appManager = appManager;
        fillChoiceBoxes();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //searchRecipeButton.setOnAction(event -> appManager.switchTo(View.RESULT));
        returnButton.setOnAction(event -> appManager.switchTo(View.MAIN));
        searchRecipeButton.setOnAction(event -> selectedIngredients());
    }


    private boolean isVegetarian(boolean isVegetarian) {
        // TODO change name an code isVegetarian() body
        boolean findAppropriateName = isVegetarian;
        return findAppropriateName;
    }

    private void selectedIngredients(){
        String selectedCuisine = chooseCuisineBox.getValue();

        String selectedIngredientOne = checkNullChoiceBoxes(chooseIngredientBox1);
        String selectedIngredientTwo = checkNullChoiceBoxes(chooseIngredientBox2);
        String selectedIngredientThree = checkNullChoiceBoxes(chooseIngredientBox3);

        List<String> choices = new ArrayList<>(Arrays.asList(selectedIngredientOne, selectedIngredientTwo, selectedIngredientThree));
        String searchString = String.join(",", choices);

        appManager.setSearchQuery(searchString);
        appManager.switchTo(View.RESULT);
    }

    private String checkNullChoiceBoxes(ChoiceBox<Ingredient> choiceBox){
        String empty = "";
        if (choiceBox.getValue() != null) {
            return choiceBox.getValue().toString();
        }else return empty;
    }

    private void fillChoiceBoxes() {
        this.ingredientsInFridge = appManager.getIngredientsInFridge();

        chooseCuisineBox.getItems().addAll(cuisines);

        chooseIngredientBox1.getItems().addAll(ingredientsInFridge);
        chooseIngredientBox2.getItems().addAll(ingredientsInFridge);
        chooseIngredientBox3.getItems().addAll(ingredientsInFridge);

        addListener(chooseIngredientBox1);
        addListener(chooseIngredientBox2);
        addListener(chooseIngredientBox3);
    }

    private void addListener(ChoiceBox<Ingredient> box) {
        box.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newValue) -> {
            updateChoiceBoxes();
        });

    }

    private void updateChoiceBoxes() {
        if (isUpdatingChoiceBoxes) return;
        isUpdatingChoiceBoxes = true;

        Ingredient selectedOne = chooseIngredientBox1.getValue();
        Ingredient selectedTwo = chooseIngredientBox2.getValue();
        Ingredient selectedThree = chooseIngredientBox3.getValue();

        List<Ingredient> selected = new ArrayList<>();
        if (selectedOne != null){
            selected.add(selectedOne);
        }
        if (selectedTwo != null) {
            selected.add(selectedTwo);
        }
        if (selectedThree != null){
            selected.add(selectedThree);
        }

        updateChoiceBox(chooseIngredientBox1, selectedOne, selected);
        updateChoiceBox(chooseIngredientBox2, selectedTwo, selected);
        updateChoiceBox(chooseIngredientBox3, selectedThree, selected);

        isUpdatingChoiceBoxes = false;

    }

    private void updateChoiceBox(ChoiceBox<Ingredient> box, Ingredient currentIngredient, List<Ingredient> selected) {
        List<Ingredient> stillAvailable = new ArrayList<>(ingredientsInFridge);
        stillAvailable.removeAll(selected);

        if (currentIngredient != null){
            stillAvailable.add(currentIngredient);
        }
        box.getItems().setAll(stillAvailable);
        box.setValue(currentIngredient);
    }

}

