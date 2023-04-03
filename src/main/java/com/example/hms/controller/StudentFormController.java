package com.example.hms.controller;

import com.example.hms.dto.StudentDto;
import com.example.hms.service.ServiceFactory;
import com.example.hms.service.custom.StudentService;
import com.example.hms.service.util.ServiceType;
import com.example.hms.util.FactoryConfiguration;
import com.example.hms.util.regex.RegExFactory;
import com.example.hms.util.regex.RegExType;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class StudentFormController implements Initializable {

    public DatePicker cmbDob;
    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<StudentDto> tblStudents;

    @FXML
    private TableColumn<StudentDto, String> colStudentId;

    @FXML
    private TableColumn<StudentDto, String> colStudentName;

    @FXML
    private TableColumn<StudentDto, String> colAddress;

    @FXML
    private TableColumn<StudentDto, String> colContact;

    @FXML
    private TableColumn<StudentDto, String> colDob;

    @FXML
    private TableColumn<StudentDto, String> colGender;

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
    private StudentService studentService;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        try {
            if (validation()) {
                StudentDto studentDto = new StudentDto();
                studentDto.setStudent_id(txtId.getText());
                studentDto.setName(txtName.getText());
                studentDto.setAddress(txtAddress.getText());
                studentDto.setGender((rBtnMale.isSelected()) ? "Male" : "Female");
                studentDto.setContact_no(txtContact.getText());
                studentDto.setDob(Date.valueOf(cmbDob.getValue()));

                studentService.save(studentDto, FactoryConfiguration.getFactoryConfiguration().getSession());
                new Alert(Alert.AlertType.INFORMATION, "Student Added").show();
                clearAll();
                refreshTable();
            } else {
                throw new RuntimeException("invalid input data in fields!");
            }
        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        StudentDto selectedItem = tblStudents.getSelectionModel().getSelectedItem();
        try {
            if (selectedItem != null) {
                studentService.delete(selectedItem, FactoryConfiguration.getFactoryConfiguration().getSession());
                new Alert(Alert.AlertType.INFORMATION, "Student Deleted").show();
                refreshTable();
                clearAll();

            } else {
                new Alert(Alert.AlertType.ERROR, "Select Student first!").show();
            }
        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        btnUpdate.setDisable(true);
        btnAdd.setDisable(false);
        btnDelete.setDisable(true);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            if (validation()) {
                StudentDto studentDto = new StudentDto();

                studentDto.setStudent_id(txtId.getText());
                studentDto.setName(txtName.getText());
                studentDto.setAddress(txtAddress.getText());
                studentDto.setGender((rBtnMale.isSelected()) ? "Male" : "Female");
                studentDto.setContact_no(txtContact.getText());
                studentDto.setDob(Date.valueOf(cmbDob.getValue()));

                studentService.update(studentDto, FactoryConfiguration.getFactoryConfiguration().getSession());
                new Alert(Alert.AlertType.INFORMATION, "Student Updated").show();

                clearAll();
                refreshTable();

            } else {
                throw new RuntimeException("invalid input data in fields!");
            }
        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        btnUpdate.setDisable(true);
        btnAdd.setDisable(false);
        btnDelete.setDisable(true);
    }

    private void clearAll() {
        txtContact.clear();
        txtAddress.clear();
        txtContact.clear();
        txtName.clear();
    }

    private boolean validation() {
        return RegExFactory.getInstance().getPattern(RegExType.NAME).matcher(txtName.getText()).matches() && RegExFactory.getInstance().getPattern(RegExType.CITY).matcher(txtAddress.getText()).matches() && RegExFactory.getInstance().getPattern(RegExType.MOBILE).matcher(txtContact.getText()).matches() && cmbDob.getValue() != null;
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        if (RegExFactory.getInstance().getPattern(RegExType.STUDENT_ID).matcher(txtSearch.getText()).matches()) {

            StudentDto studentDto = new StudentDto();
            studentDto.setStudent_id(txtSearch.getText());
//            studentService.view(studentDto, FactoryConfiguration.getFactoryConfiguration().getSession());
        }
    }

    @FXML
    void txtSearchOnKeyReleased(KeyEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        studentService = ServiceFactory.getServiceFactory().getService(ServiceType.StudentService);

        colStudentId.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact_no"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        refreshTable();

    }

    private void refreshTable() {
        try {
            generateStudentId();
            List all = studentService.getAll(FactoryConfiguration.getFactoryConfiguration().getSession());
            ObservableList<StudentDto> studentDtoObservableList = FXCollections.observableArrayList();
            studentDtoObservableList.addAll(all);
            tblStudents.setItems(studentDtoObservableList);
        } catch (RuntimeException exception) {
             new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
             tblStudents.getItems().clear();
        }
    }

    private void generateStudentId() {
        txtId.setText(studentService.getLastId(FactoryConfiguration.getFactoryConfiguration().getSession()));
    }

    public void tblStudentOnMouseClicked(MouseEvent mouseEvent) {
        StudentDto studentDto = tblStudents.getSelectionModel().getSelectedItem();
        try {
            if (studentDto != null) {
                btnUpdate.setDisable(false);
                btnDelete.setDisable(false);
                btnAdd.setDisable(true);
//                studentService.view(selectedItem, FactoryConfiguration.getFactoryConfiguration().getSession());
                txtId.setText(studentDto.getStudent_id());
                txtName.setText(studentDto.getName());
                txtAddress.setText(studentDto.getAddress());
                if (studentDto.getGender().equals("Male")) {
                    rBtnMale.setSelected(true);
                } else {
                    rBtnFemale.setSelected(true);
                }
                txtContact.setText(studentDto.getContact_no());
                cmbDob.setValue(studentDto.getDob().toLocalDate());
            } else {
                btnUpdate.setDisable(true);
            }
        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
