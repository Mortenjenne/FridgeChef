package io.github.mortenjenne.fridgechef.controller;

import io.github.mortenjenne.fridgechef.logic.AppManager;
import io.github.mortenjenne.fridgechef.logic.SceneController;
import io.github.mortenjenne.fridgechef.logic.View;
import io.github.mortenjenne.fridgechef.model.Dish;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ResultController implements Initializable, SceneController {
    private AppManager appManager;

    @FXML private Label dishOne;
    @FXML private Label dishTwo;
    @FXML private Label dishThree;
    @FXML private Label dishFour;
    @FXML private Label dishFive;
    @FXML private Label dishSix;
    @FXML private ImageView dishOneImg;
    @FXML private ImageView dishTwoImg;
    @FXML private ImageView dishThreeImg;
    @FXML private ImageView dishFourImg;
    @FXML private ImageView dishFiveImg;
    @FXML private ImageView dishSixImg;
    @FXML private Button returnButton;

    private List<Dish> searchResult;

    @Override
    public void setAppManager(AppManager appManager) {
        this.appManager = appManager;
        this.searchResult = appManager.searchRecipesByIngredientList(appManager.getSearchQuery());
        showSearchResult();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        returnButton.setOnAction(event -> appManager.switchTo(View.SEARCH));
        dishOne.setOnMouseClicked(event -> appManager.switchTo(View.RECIPE));
        // dishOneImg.setOnMouseClicked(event -> appManager.switchTo(View.RECIPE));
    }

    public void showSearchResult() {

        if (searchResult.size() >= 3) {
            //dishOneImg.setImage(new Image(dishes.get(0).getImageUrl()));
            dishOne.setText(searchResult.get(0).getTitle());

            //recipeImage2.setImage(new Image(dishes.get(1).getImageUrl()));
            dishTwo.setText(searchResult.get(1).getTitle());

            //recipeImage3.setImage(new Image(dishes.get(2).getImageUrl()));
            dishThree.setText(searchResult.get(2).getTitle());
        }
    }

}
