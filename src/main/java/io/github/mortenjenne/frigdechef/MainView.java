package io.github.mortenjenne.frigdechef;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainView extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainView.class.getResource("LoginScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 800);
        Image img = new Image(getClass().getResourceAsStream("fridgechef.png"));
        stage.getIcons().add(img);
        stage.setTitle("FridgeChef");
        stage.setScene(scene);
        stage.show();
    }
}