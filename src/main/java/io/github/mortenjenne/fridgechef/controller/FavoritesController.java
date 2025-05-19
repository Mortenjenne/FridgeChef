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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FavoritesController implements Initializable, SceneController {
    @FXML
    private Label dishOne, dishTwo, dishThree, dishFour, dishFive, dishSix, dishSeven, dishEight, dishNine;
    @FXML
    private ImageView dishOneImg, dishTwoImg, dishThreeImg, dishFourImg, dishFiveImg, dishSixImg, dishSevenImg, dishEightImg, dishNineImg;
    @FXML
    private Button returnButton, prevButton, nextButton;

    private AppManager appManager;
    private List<Dish> favoriteDishes;
    private List<ImageView> views = new ArrayList<>();
    private List<Label> labels = new ArrayList<>();
    private int currentPage = 0;
    private final int resultPerPage = 9;

    @Override
    public void setAppManager(AppManager appManager) {
        this.appManager = appManager;
        this.favoriteDishes = appManager.getCurrentUser().getFavoriteDishes();
        showFavoritesResult();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnButton.setOnAction(event -> appManager.switchTo(View.MAIN));

        dishOne.setOnMouseClicked(event -> showRecipe(0));
        dishOneImg.setOnMouseClicked(event -> showRecipe(0));

        dishTwo.setOnMouseClicked(event -> showRecipe(1));
        dishTwoImg.setOnMouseClicked(event -> showRecipe(1));

        dishThree.setOnMouseClicked(event -> showRecipe(2));
        dishThreeImg.setOnMouseClicked(event -> showRecipe(2));

        dishFour.setOnMouseClicked(event -> showRecipe(3));
        dishFourImg.setOnMouseClicked(event -> showRecipe(3));

        dishFive.setOnMouseClicked(event -> showRecipe(4));
        dishFiveImg.setOnMouseClicked(event ->showRecipe(4));

        dishSix.setOnMouseClicked(event -> showRecipe(5));
        dishSixImg.setOnMouseClicked(event -> showRecipe(5));

        dishSeven.setOnMouseClicked(event -> showRecipe(6));
        dishSevenImg.setOnMouseClicked(event -> showRecipe(6));

        dishEight.setOnMouseClicked(event -> showRecipe(7));
        dishEightImg.setOnMouseClicked(event -> showRecipe(7));

        dishNine.setOnMouseClicked(event -> showRecipe(8));
        dishNineImg.setOnMouseClicked(event -> showRecipe(8));


        prevButton.setOnAction(event -> {
            this.currentPage--;
            updatePage();
        });

        nextButton.setOnAction(event -> {
            this.currentPage++;
            updatePage();
        });
    }

    private void showFavoritesResult() {
        addViewsToList();
        labelsToList();
        updatePage();
    }

    private void addViewsToList() {
        views.addAll(List.of(dishOneImg, dishTwoImg, dishThreeImg, dishFourImg, dishFiveImg, dishSixImg, dishSevenImg, dishEightImg, dishNineImg));
    }

    private void labelsToList() {
        labels.addAll(List.of(dishOne, dishTwo, dishThree, dishFour, dishFive, dishSix, dishSeven, dishEight, dishNine));
    }

    private void showRecipe(int index){
        int dishIndex = currentPage * resultPerPage + index;
        if(dishIndex < favoriteDishes.size()) {
            Dish selected = favoriteDishes.get(dishIndex);

            appManager.setSelectedRecipe(selected);
            appManager.switchTo(View.RECIPE);
            appManager.setShowRecipeFromFavorites(true);
        }
    }

    private void updatePage() {
        int start = currentPage * resultPerPage;
        int end = Math.min(start + resultPerPage, favoriteDishes.size());

        for (int i = 0; i < views.size(); i++) {
            views.get(i).setImage(null);
            labels.get(i).setText("");
        }

        if (favoriteDishes.isEmpty()) {
            labels.get(1).setText("No Favorites Added.");
            prevButton.setDisable(true);
            nextButton.setDisable(true);

        } else {
            int index = 0;
            for (int i = start; i < end; i++) {
                Dish dish = favoriteDishes.get(i);
                labels.get(index).setText(dish.getTitle());
                views.get(index).setImage(new Image(dish.getImageUrl()));
                index++;
            }
        }
        prevButton.setDisable(currentPage == 0);
        nextButton.setDisable(currentPage * resultPerPage + resultPerPage >= favoriteDishes.size());
    }
}
