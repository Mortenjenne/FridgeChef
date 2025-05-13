package io.github.mortenjenne.fridgechef.logic;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SceneNavigator {
    private Stage stage;
    private AppManager appManager;
    private Map<View, Parent> cache = new HashMap<>();

    public SceneNavigator(Stage stage) {
        this.stage = stage;
    }

    public void setAppManager(AppManager appManager){
        this.appManager = appManager;
    }

    public void switchTo(View view) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(view.getFileName()));
            Parent root = loader.load();
            setController(view, loader);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.out.println("Error loading scene " + e.getMessage());
        }
    }

    private void setController(View view, FXMLLoader loader) {
        Object controller = loader.getController();
        if (controller instanceof SceneController) {
            ((SceneController) controller).setAppManager(appManager);
        }
    }
}
