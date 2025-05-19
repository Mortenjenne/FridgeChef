package io.github.mortenjenne.fridgechef.controller;

import io.github.mortenjenne.fridgechef.logic.AppManager;
import io.github.mortenjenne.fridgechef.logic.SceneController;
import io.github.mortenjenne.fridgechef.logic.View;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateAccountController implements Initializable, SceneController {
    @FXML private Label firstNameErrorLabel;
    @FXML private Label emailErrorLabel;
    @FXML private Label passwordErrorLabel;
    @FXML private Label passwordRequirementLabel;
    @FXML private TextField firstNameTextField;
    @FXML private TextField emailTextField;
    @FXML private PasswordField passwordTextField;
    @FXML private PasswordField retypePasswordTextField;
    @FXML private Button createAccountButton;
    @FXML private Button returnButton;

    private AppManager appManager;
    private String userName;
    private String email;
    private String password;
    private String confirmPassword;

    @Override
    public void setAppManager(AppManager appManager) {
        this.appManager = appManager;
        passwordRequirementLabel.setText("Requirements: min. 6 characters which includes 1 upper, 1 lower & 1 digit.");
    }

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
                appManager.setAccountCreated(true);
                appManager.switchTo(View.LOGIN);
            }
        });
    }

    private boolean validateUserInfo(String userName, String email, String password1, String password2){
        firstNameErrorLabel.setText("");
        emailErrorLabel.setText("");
        passwordErrorLabel.setText("");

        if(!isUserNameValid(userName)){
            firstNameErrorLabel.setText("Your account name has to have a minimum of 2 characters.");
            return false;
        }

        if(!isEmailValid(email)){
            emailErrorLabel.setText("Please enter a valid e-mail address.");
            return false;
        }

        if(appManager.isEmailInSystem(email)){
            emailErrorLabel.setText("Email already registered. Please use a different email address.");
            return false;
        }

        if(!isValidPassword(password1)){
            passwordErrorLabel.setText("Invalid password");
            return false;
        }

        if(!isPasswordIdentical(password1,password2)){
            passwordErrorLabel.setText("Passwords are not identical");
            return false;
        }
        return true;
    }

    private boolean isValidPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            return false;
        }
        if (password.length() < 6) {
            return false;
        }
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }
        if (!password.matches(".*[a-z].*")) {
            return false;
        }
        if (!password.matches(".*\\d.*")) {
            return false;
        }
        return true;
    }

    private boolean isPasswordIdentical(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    private boolean isEmailValid(String email) {
        if (!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            return false;
        }
        return true;
    }

    private boolean isUserNameValid(String userName) {
        return userName.trim().length() >= 2;
    }
}
