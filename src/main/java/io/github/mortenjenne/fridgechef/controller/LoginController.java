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
    @FXML private Button loginButton;
    @FXML private Button exitButton;
    @FXML private Label createAccountLabel;
    @FXML private Label accountCreatedLabel;
    @FXML private PasswordField passwordField;
    @FXML private TextField emailTextField;
    @FXML private Label loginErrorLabel;

    private AppManager appManager;
    private String email;
    private String password;

    @Override
    public void setAppManager(AppManager appManager) {
        this.appManager = appManager;
        if(appManager.getAccountCreated()){
            String accountCreatedMessage = "Account created successfully!";
            accountCreatedLabel.setText(accountCreatedMessage);
        }
    }

    private void login(String email, String password){
        if(appManager.login(email,password)){
            appManager.switchTo(View.MAIN);
        } else {
            String loginErrorMessage = "Invalid username or password. Please try again.";
            loginErrorLabel.setText(loginErrorMessage);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginButton.setOnAction(event -> {
            email = emailTextField.getText();
            password = passwordField.getText();
            login(email,password);
        });
        createAccountLabel.setOnMouseClicked(event -> appManager.switchTo(View.CREATE));
        exitButton.setOnAction(event -> System.exit(0));
    }
}
