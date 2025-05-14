package io.github.mortenjenne.fridgechef.controller;

import io.github.mortenjenne.fridgechef.logic.AppManager;
import io.github.mortenjenne.fridgechef.logic.SceneController;
import io.github.mortenjenne.fridgechef.logic.View;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable, SceneController {
       private AppManager appManager;

       private String email;
       private String password;

    @FXML private Button loginButton;
    @FXML private Button exitButton;
    @FXML private Label createAccountLabel;
    @FXML private PasswordField passwordField;
    @FXML private TextField emailTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginButton.setOnAction(event -> {
            email = emailTextField.getText();
            password = passwordField.getText();
            login(email,password);
        });
        createAccountLabel.setOnMouseClicked(event -> appManager.switchTo(View.CREATE));
        exitButton.setOnAction(event -> closeApp());
    }

    @Override
    public void setAppManager(AppManager appManager) {
        this.appManager = appManager;
    }

    private void login(String email, String password){
        // TODO login() body
        if(appManager.login(email,password)){
            appManager.switchTo(View.MAIN);
        }
    }

    private void signUp(){
        // TODO signUp() body
    }

    private void closeApp(){
        // TODO closeApp() body
        String message = "";
        System.out.println(message);

        // Test if System.exit works with JavaFX
        System.exit(0);
    }
}
