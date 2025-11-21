package io.github.mortenjenne.fridgechef;

import io.github.mortenjenne.fridgechef.logic.AppManager;
import io.github.mortenjenne.fridgechef.logic.RecipeManager;
import io.github.mortenjenne.fridgechef.logic.SceneNavigator;
import io.github.mortenjenne.fridgechef.logic.View;
import io.github.mortenjenne.fridgechef.model.Account;
import io.github.mortenjenne.fridgechef.util.ConnectionPool;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class FridgeChefApp extends Application {

    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/%s?currentSchema=public";
    private static final String DB = "fridgechef";

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        // Initialize the connection pool at application startup
        ConnectionPool.getInstance(USER, PASSWORD, URL, DB);

        SceneNavigator sceneNavigator = new SceneNavigator(stage);
        RecipeManager recipeManager = new RecipeManager();
        Account account = new Account();
        AppManager manager = new AppManager(sceneNavigator,recipeManager,account);
        sceneNavigator.setAppManager(manager);

        manager.switchTo(View.LOGIN);

        Image img = new Image(getClass().getResourceAsStream("fridgechef.png"));
        stage.getIcons().add(img);
        stage.setTitle("FridgeChef");

        stage.show();
    }

    @Override
    public void stop() throws Exception {
        // Close the connection pool when the application stops
        ConnectionPool.getInstance().close();
        super.stop();
    }
}