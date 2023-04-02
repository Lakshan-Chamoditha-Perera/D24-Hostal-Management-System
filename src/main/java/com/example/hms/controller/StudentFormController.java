package com.example.hms.controller;

import com.example.hms.service.ServiceFactory;
import com.example.hms.service.custom.StudentService;
import com.example.hms.service.util.ServiceType;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentFormController implements Initializable {

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<?> tblStudents;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colStudentName;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colDob;

    @FXML
    private TableColumn<?, ?> colGender;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private RadioButton rBtnMale;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton rBtnFemale;

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchOnKeyReleased(KeyEvent event) {

    }
private StudentService studentService;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        studentService = ServiceFactory.getServiceFactory().getService(ServiceType.StudentService);


    }
}
