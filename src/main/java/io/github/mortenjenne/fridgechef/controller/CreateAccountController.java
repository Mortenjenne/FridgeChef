package io.github.mortenjenne.fridgechef.controller;

import io.github.mortenjenne.fridgechef.logic.AppManager;
import io.github.mortenjenne.fridgechef.logic.SceneController;
import io.github.mortenjenne.fridgechef.logic.SceneNavigator;
import io.github.mortenjenne.fridgechef.logic.View;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


import java.net.URL;
import java.util.ResourceBundle;

public class CreateAccountController implements Initializable, SceneController {
    private AppManager appManager;
    private String userName;
    private String email;
    String password;
    String confirmPassword;
    String passwordRequirements = "Enter a password:\nRequirements: at least 6 characters, at least 1 uppercase letter, at least 1 lowercase letter, at least 1 number:";

    //@FXML private Label nameErrorLabel;
    @FXML private Label emailErrorLabel;
    @FXML private Label passwordErrorLabel;
    @FXML private TextField firstNameTextField;
    @FXML private TextField emailTextField;
    @FXML private PasswordField passwordTextField;
    @FXML private PasswordField retypePasswordTextField;
    @FXML private Button createAccountButton;
    @FXML private Button returnButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        returnButton.setOnAction(event -> appManager.switchTo(View.LOGIN));
        createAccountButton.setOnAction(event -> {
            userName = firstNameTextField.getText();
            email = emailTextField.getText();
            password = passwordTextField.getText();
            confirmPassword = retypePasswordTextField.getText();

            if (validateUserInfo(userName,email, password, confirmPassword)) {
                appManager.createAccount(userName,email, password);
                appManager.switchTo(View.LOGIN);
            }
        });
    }

    @Override
    public void setAppManager(AppManager appManager) {
        this.appManager = appManager;
    }

    private boolean validateUserInfo(String userName, String email, String password1, String password2){
        //nameErrorLabel.setText("");
        emailErrorLabel.setText("");
        passwordErrorLabel.setText("");

        if(!appManager.isUserNameValid(userName)){
            emailErrorLabel.setText("Your account name has to have a minimum of 2 characters.");
            return false;
        }
        if(!appManager.isEmailValid(email)){
            emailErrorLabel.setText("Please enter a valid e-mail address.");
            return false;
        }

        if(appManager.isEmailInSystem(email)){
            emailErrorLabel.setText("Email already registered. Please use a different email address.");
            return false;
        }

        if(!appManager.isValidPassword(password1)){
            passwordErrorLabel.setText("Invalid password: min. 6 chars, 1 upper, 1 lower, 1 digit.");
            return false;
        }
        if(!appManager.isPasswordIndentical(password1,password2)){
            passwordErrorLabel.setText("Passwords are not identical");
            return false;
        }
        return true;
    }
}
