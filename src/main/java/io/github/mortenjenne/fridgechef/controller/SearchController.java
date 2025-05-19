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
import java.util.*;
import java.util.stream.Stream;

public class SearchController implements Initializable, SceneController {
    @FXML private Button searchRecipeButton;
    @FXML private CheckBox isVegan;
    @FXML private CheckBox isVegetarian;
    @FXML private CheckBox isGlutenFree;
    @FXML private CheckBox isLactoseFree;
    @FXML private ChoiceBox<String> chooseCuisineBox;
    @FXML private ChoiceBox<Ingredient> chooseIngredientBox1;
    @FXML private ChoiceBox<Ingredient> chooseIngredientBox2;
    @FXML private ChoiceBox<Ingredient> chooseIngredientBox3;
    @FXML private Button returnButton;
    @FXML private Button clearCuisineButton;
    @FXML private Button clearIngredientButton1;
    @FXML private Button clearIngredientButton2;
    @FXML private Button clearIngredientButton3;

    private AppManager appManager;
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
        returnButton.setOnAction(event -> appManager.switchTo(View.MAIN));
        searchRecipeButton.setOnAction(event -> selectedIngredients());
        clearCuisineButton.setOnAction(event -> clearCuisineChoice(chooseCuisineBox));
        clearIngredientButton1.setOnAction(event -> clearIngredientChoice(chooseIngredientBox1));
        clearIngredientButton2.setOnAction(event -> clearIngredientChoice(chooseIngredientBox2));
        clearIngredientButton3.setOnAction(event -> clearIngredientChoice(chooseIngredientBox3));
    }

    private void clearIngredientChoice(ChoiceBox<Ingredient> box){
        box.setValue(null);
    }

    private void clearCuisineChoice(ChoiceBox<String> box){
        box.setValue("");
    }

    private void selectedIngredients(){
        String selectedCuisine = chooseCuisineBox.getValue();
        String selectedIngredientOne = checkNullChoiceBoxes(chooseIngredientBox1);
        String selectedIngredientTwo = checkNullChoiceBoxes(chooseIngredientBox2);
        String selectedIngredientThree = checkNullChoiceBoxes(chooseIngredientBox3);

        // Create List as Stream - remove values if object is null or empty
        List<String> choices = Stream.of(selectedIngredientOne, selectedIngredientTwo, selectedIngredientThree).filter(Objects::nonNull).filter(s -> !s.isEmpty()).toList();

        // Separate values with ','
        String searchString = String.join(",", choices);

        if (isVegan.isSelected()) {
            appManager.setIsSearchVegan(true);
            appManager.setIsSearchVegetarian(false);
        } else if (isVegetarian.isSelected()) {
            appManager.setIsSearchVegetarian(true);
            appManager.setIsSearchVegan(false);
        } else {
            appManager.setIsSearchVegan(false);
            appManager.setIsSearchVegetarian(false);
        }

        if(isGlutenFree.isSelected() & isLactoseFree.isSelected()){
            appManager.setIntolerances("gluten,dairy");
        } else if(isLactoseFree.isSelected()){
            appManager.setIntolerances("dairy");
        } else if(isGlutenFree.isSelected()){
            appManager.setIntolerances("gluten");
        } else {
            appManager.setIntolerances("");
        }

        if(selectedCuisine == null || selectedCuisine.isEmpty()){
            appManager.setCuisineQuery("");
        } else {
            appManager.setCuisineQuery(selectedCuisine);
        }
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