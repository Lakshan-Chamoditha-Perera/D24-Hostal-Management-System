package com.example.hms.controller;


import com.example.hms.dto.UserDto;
import com.example.hms.service.ServiceFactory;
import com.example.hms.service.custom.UserService;
import com.example.hms.service.util.ServiceType;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserFormController implements Initializable {

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<UserDto> tblUsers;

    @FXML
    private TableColumn<UserDto, String> colId;

    @FXML
    private TableColumn<UserDto, String> colPassword;

    @FXML
    private TableColumn<UserDto, String> colPasswordHInt;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtPassword;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private TextField txtPasswordHint;
    private UserService userService;

    @FXML
    void btnAddOnAction(ActionEvent event) throws IOException {
        pane.setDisable(true);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setAlwaysOnTop(true);
        stage.requestFocus();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/SignUpPage.fxml"))));
        stage.setTitle("Sign-up");
        stage.showAndWait();
        refreshTable();
        pane.setFocusTraversable(true);
        pane.setDisable(false);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            UserDto selectedItem = tblUsers.getSelectionModel().getSelectedItem();
            userService.delete(selectedItem.getId());
            new Alert(Alert.AlertType.ERROR, "User Deleted").showAndWait();
        } catch (RuntimeException exception) {
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
        }
        tblUsers.getItems().clear();
        btnAdd.setDisable(false);
        refreshTable();
        clear();
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            UserDto userDto = new UserDto();
            userDto.setId(txtId.getText());
            userDto.setPassword(txtPassword.getText());
            userDto.setPasswordHint(txtPasswordHint.getText());
            userService.update(userDto);
            new Alert(Alert.AlertType.ERROR, "User Updated").showAndWait();
        } catch (RuntimeException exception) {
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
        }
        tblUsers.getItems().clear();
        btnAdd.setDisable(false);
        refreshTable();
        clear();
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userService = ServiceFactory.getServiceFactory().getService(ServiceType.UserService);

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colPasswordHInt.setCellValueFactory(new PropertyValueFactory<>("passwordHint"));

        refreshTable();
    }

    private void refreshTable() {
        try {
            List<UserDto> allUsers = userService.getAll();
            ObservableList<UserDto> observableList = FXCollections.observableArrayList();
            observableList.addAll(allUsers);
            tblUsers.setItems(observableList);
        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void clear(){
        txtId.clear();
        txtPassword.clear();
        txtPasswordHint.clear();
    }


    public void tblUsersOnMouseClicked(MouseEvent mouseEvent) {
        UserDto selectedItem = tblUsers.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            btnAdd.setDisable(true);
            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);

            txtId.setText(selectedItem.getId());
            txtPassword.setText(selectedItem.getPassword());
            txtPasswordHint.setText(selectedItem.getPasswordHint());
        }
    }
}
