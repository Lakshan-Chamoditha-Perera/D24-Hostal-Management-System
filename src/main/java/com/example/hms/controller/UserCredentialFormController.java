package com.example.hms.controller;

import com.example.hms.dto.UserDto;
import com.example.hms.service.ServiceFactory;
import com.example.hms.service.custom.UserService;
import com.example.hms.service.util.ServiceType;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserCredentialFormController implements Initializable {

    DashboardController dashboardController;
    @FXML
    private AnchorPane floatingPane;
    @FXML
    private JFXButton btnCheck;
    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXTextField txtPassword;
    private UserService userService;

    @FXML
    void btnCheckOnAction(ActionEvent event) throws IOException {
        try {
            if (txtUsername.getText() != null && txtPassword.getText() != null) {
                UserDto view = userService.view(txtUsername.getText());
                if (view.getPassword().equals(txtPassword.getText())) {
                    Stage stage = (Stage) floatingPane.getScene().getWindow();
                    stage.close();
                    dashboardController.navigate();
                    return;
                }
                throw new RuntimeException("Invalid Password");
            }
            throw new RuntimeException("Invalid input");
        } catch (RuntimeException exception) {
            Stage stage = (Stage) floatingPane.getScene().getWindow();
            stage.setAlwaysOnTop(false);
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).showAndWait();
            stage.setAlwaysOnTop(true);
        }
    }

    public void init(DashboardController dashboardController) {
        this.dashboardController = dashboardController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            userService = ServiceFactory.getServiceFactory().getService(ServiceType.UserService);
        } catch (RuntimeException exception) {
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
        }
    }
}
