package io.github.mortenjenne.fridgechef;

import io.github.mortenjenne.fridgechef.logic.AppManager;
import io.github.mortenjenne.fridgechef.logic.RecipeManager;
import io.github.mortenjenne.fridgechef.logic.SceneNavigator;
import io.github.mortenjenne.fridgechef.logic.View;
import io.github.mortenjenne.fridgechef.model.Account;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class FridgeChefApp extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        SceneNavigator sceneNavigator = new SceneNavigator(stage);
        RecipeManager recipeManager = new RecipeManager();
        Account account = new Account();
        AppManager manager = new AppManager(sceneNavigator,recipeManager,account);
        sceneNavigator.setAppManager(manager);

        manager.switchTo(View.RESULT);

        Image img = new Image(getClass().getResourceAsStream("fridgechef.png"));
        stage.getIcons().add(img);
        stage.setTitle("FridgeChef");

        stage.show();
    }
}