package com.example.hms.controller;

import com.example.hms.dto.UserDto;
import com.example.hms.service.ServiceFactory;
import com.example.hms.service.custom.UserService;
import com.example.hms.service.util.ServiceType;
import com.example.hms.util.FactoryConfiguration;
import com.example.hms.util.regex.RegExFactory;
import com.example.hms.util.regex.RegExType;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.net.URL;
import java.util.ResourceBundle;

public class SignupPageController implements Initializable {

    public TextField txtPasswordHint;
    public AnchorPane pane;
    @FXML
    private JFXButton btnRegister;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtRptPassword;

    @FXML
    private TextField TxtPasswordHint;
    private UserService userService;
    private RegExFactory regExFactory;

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        Stage window = (Stage) pane.getScene().getWindow();
        try {
            window.setAlwaysOnTop(false);
            if (checkRegEx()) {
                UserDto userDto = new UserDto(txtUsername.getText(), txtPassword.getText(), txtPasswordHint.getText());
                Session session = FactoryConfiguration.getFactoryConfiguration().getSession();
                userService.save(userDto, session);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Registration Success! ");
                alert.showAndWait();
                clear();
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Pattern not match!").showAndWait();
            }
        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.INFORMATION, e.getMessage()).showAndWait();
        }
        window.setAlwaysOnTop(true);
    }

    private void clear() {
        txtUsername.clear();
        txtPassword.clear();
        txtRptPassword.clear();
        txtPasswordHint.clear();
    }

    private boolean checkRegEx() throws RuntimeException {
        return regExFactory.getPattern(RegExType.NAME).matcher(txtUsername.getText()).matches() && regExFactory.getPattern(RegExType.PASSWORD).matcher(txtPassword.getText()).matches() && regExFactory.getPattern(RegExType.PASSWORD).matcher(txtRptPassword.getText()).matches() && txtPassword.getText().equals(txtPasswordHint.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userService = ServiceFactory.getServiceFactory().getService(ServiceType.UserService);
        regExFactory = RegExFactory.getInstance();
    }
}
