package lk.ijse.carrentalmanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.carrentalmanagementsystem.model.UserModel;
import lk.ijse.carrentalmanagementsystem.to.User;
import lk.ijse.carrentalmanagementsystem.util.Animations;
import lk.ijse.carrentalmanagementsystem.util.Navigation;
import lk.ijse.carrentalmanagementsystem.util.Router;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class LoginFormController {
    public TextField txtusername;
    public PasswordField txtpassword;
    public Button btnlogin;
    public AnchorPane loginPageAnchorPane;

    private Pattern usernamePattern;
    private Pattern passwordPattern;

    String AdminUserName = "Admin";
    String AdminPassword = "1234";


    String userUserName = "User";
    String userPassword = "12345";
    public void initialize() {
        usernamePattern = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        passwordPattern = Pattern.compile("^[0-9]{4,}");
    }

    public void loginOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        loginValidation();
    }

    private void animations() {
        Animations.shake(txtpassword);
        Animations.shake(txtusername);

    }
    public void enterKeyUsername(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            txtpassword.requestFocus();
        }
    }
    public void EnterKeyPassword(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            loginValidation();
        }
    }

    private void loginValidation() throws SQLException, ClassNotFoundException {
        String username = txtusername.getText().trim();
        String password = txtpassword.getText().trim();

        String adminUsername = txtusername.getText().trim();
        String adminPassword = txtpassword.getText().trim();


        try {
            boolean isCorrect = false;
            try {
                isCorrect= UserModel.check(adminUsername,adminPassword);
                boolean isUserNameMatched = usernamePattern.matcher(txtusername.getText()).matches();
                boolean isPasswordMatched = passwordPattern.matcher(txtpassword.getText()).matches();
                if (isUserNameMatched) {
                    if (isPasswordMatched) {
                        if (isCorrect) {
                            Navigation.navigate(Router.DASHBOARD, loginPageAnchorPane);
                        } else {
                            if (username.equals(userUserName) && password.equals(userPassword)) {
                                Navigation.navigate(Router.USERDASHBOARD, loginPageAnchorPane);
                            } else if (username.equals(AdminUserName) && password.equals(AdminPassword)){
                                Navigation.navigate(Router.DASHBOARD,loginPageAnchorPane);
                            }
                        }
                    } else {
                        txtpassword.requestFocus();
                        new Alert(Alert.AlertType.WARNING, "Password is incorrect...!").show();
                        animations();
                    }
                } else {
                    txtusername.requestFocus();
                    new Alert(Alert.AlertType.WARNING, "Username is incorrect...!").show();
                    animations();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

}







