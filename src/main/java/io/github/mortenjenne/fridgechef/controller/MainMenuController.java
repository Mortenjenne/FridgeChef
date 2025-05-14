package io.github.mortenjenne.fridgechef.controller;

import io.github.mortenjenne.fridgechef.logic.AppManager;
import io.github.mortenjenne.fridgechef.logic.SceneController;
import io.github.mortenjenne.fridgechef.logic.View;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable, SceneController {
    private AppManager appManager;

    @FXML private Button searchButton;
    @FXML private Button favoriteDishButton;
    @FXML private Button fridgeButton;
    @FXML private Button returnButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchButton.setOnAction(event -> appManager.switchTo(View.SEARCH));
        favoriteDishButton.setOnAction(event -> appManager.switchTo(View.LOGIN));
        fridgeButton.setOnAction(event -> appManager.switchTo(View.FRIDGE));
        returnButton.setOnAction(event -> appManager.switchTo(View.LOGIN));
    }


    @Override
    public void setAppManager(AppManager appManager) {
        this.appManager = appManager;
    }
}
