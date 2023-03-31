package com.example.hms.controller;

import com.example.hms.dto.UserDto;
import com.example.hms.service.ServiceFactory;
import com.example.hms.service.custom.UserService;
import com.example.hms.service.util.ServiceType;
import com.example.hms.util.FactoryConfiguration;
import com.example.hms.util.NavigationFactory;
import com.example.hms.util.navigation.NavigationType;
import com.example.hms.util.regex.RegExFactory;
import com.example.hms.util.regex.RegExType;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {

    public AnchorPane pane;
    public Label lblPassword;
    public RadioButton rBtnShowPassword;
    @FXML
    private TextField txtId;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXButton btnRegister;

    private UserService userService;

    @FXML
    void btnLoginOnAction(ActionEvent event) {
//        checkRegEx()
        if (true) {
            try {
                UserDto dto = new UserDto(txtId.getText(), txtPassword.getText(), "hint");
                UserDto user = userService.view(dto, FactoryConfiguration.getFactoryConfiguration().getSession());
                if (user.getId().equals(txtId.getText()) && user.getPassword().equals(txtPassword.getText())) {
                    NavigationFactory.getInstance().navigate(NavigationType.DASHBOARD, pane);
                }
            } catch (RuntimeException | IOException exception) {
                new Alert(Alert.AlertType.INFORMATION, exception.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Invalid Input!").show();
        }
    }

    private boolean checkRegEx() throws RuntimeException {
        return RegExFactory.getInstance().getPattern(RegExType.NAME).matcher(txtId.getText()).matches() && RegExFactory.getInstance().getPattern(RegExType.PASSWORD).matcher(txtPassword.getText()).matches();
    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) throws IOException {
        //  pane.setDisable(true);
        pane.setFocusTraversable(false);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setAlwaysOnTop(true);
        stage.centerOnScreen();
        stage.requestFocus();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/SignUpPage.fxml"))));
        stage.setTitle("Sign-up");
        stage.showAndWait();

        pane.setFocusTraversable(true);
        pane.setDisable(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userService = ServiceFactory.getServiceFactory().getService(ServiceType.UserService);
    }

    public void txtPasswordOnKeyReleased(KeyEvent keyEvent) {
        rBtnOnMouseClicked();
    }

    public void rBtnOnMouseClicked() {
        lblPassword.setText(txtPassword.getText());
        lblPassword.setVisible(rBtnShowPassword.isSelected());
    }
}
