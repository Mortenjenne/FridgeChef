package io.github.mortenjenne.fridgechef;

import io.github.mortenjenne.fridgechef.logic.AppManager;
import io.github.mortenjenne.fridgechef.logic.RecipeManager;
import io.github.mortenjenne.fridgechef.logic.SceneNavigator;
import io.github.mortenjenne.fridgechef.logic.View;
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
        AppManager manager = new AppManager(sceneNavigator,recipeManager);
        sceneNavigator.setAppManager(manager);

        manager.switchTo(View.LOGIN);

        Image img = new Image(getClass().getResourceAsStream("fridgechef.png"));
        stage.getIcons().add(img);
        stage.setTitle("FridgeChef");

        stage.show();
    }
}