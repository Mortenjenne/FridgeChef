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

    @FXML private Button loginButton;
    @FXML private Button exitButton;
    @FXML private Label createAccountLabel;
    @FXML private PasswordField passwordTextField;
    @FXML private TextField emailTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginButton.setOnAction(event -> appManager.switchTo(View.MAIN));
        createAccountLabel.setOnMouseClicked(event -> appManager.switchTo(View.CREATE));
        exitButton.setOnAction(event -> closeApp());
    }

    @Override
    public void setAppManager(AppManager appManager) {
        this.appManager = appManager;
    }

    private void login(){
        // TODO login() body
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        appManager.login(email,password);
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
