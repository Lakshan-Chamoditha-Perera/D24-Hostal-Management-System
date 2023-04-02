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
import javafx.scene.layout.AnchorPane;

import java.net.URL;
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
    private TableColumn <StudentDto,String>colAddress;

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
                studentDto.setDob(String.valueOf(cmbDob.getValue()));

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

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

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
        }
    }

    private void generateStudentId() {
        txtId.setText(studentService.getLastId(FactoryConfiguration.getFactoryConfiguration().getSession()));
    }
}
