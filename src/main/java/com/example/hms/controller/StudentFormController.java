package com.example.hms.controller;

import com.example.hms.dto.StudentDto;
import com.example.hms.service.ServiceFactory;
import com.example.hms.service.custom.StudentService;
import com.example.hms.service.util.ServiceType;
import com.example.hms.tm.StudentTm;
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
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class StudentFormController implements Initializable {

    public DatePicker cmbDob;
    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<StudentTm> tblStudents;

    @FXML
    private TableColumn<StudentTm, String> colStudentId;

    @FXML
    private TableColumn<StudentTm, String> colStudentName;

    @FXML
    private TableColumn<StudentTm, String> colAddress;

    @FXML
    private TableColumn<StudentTm, String> colContact;

    @FXML
    private TableColumn<StudentTm, Date> colDob;

    @FXML
    private TableColumn<StudentTm, String> colGender;

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

                studentService.save(studentDto);
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
        StudentTm selectedItem = tblStudents.getSelectionModel().getSelectedItem();
        try {
            if (selectedItem != null) {
                studentService.delete(selectedItem.getStudent_id());
                new Alert(Alert.AlertType.INFORMATION, "Student Deleted").show();
                refreshTable();
                clearAll();

            } else {
                new Alert(Alert.AlertType.ERROR, "Select Student first!").show();
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
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

                studentService.update(studentDto);
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
        try{
            studentService = ServiceFactory.getServiceFactory().getService(ServiceType.StudentService);

            colStudentId.setCellValueFactory(new PropertyValueFactory<>("student_id"));
            colStudentName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colContact.setCellValueFactory(new PropertyValueFactory<>("contact_no"));
            colDob.setCellValueFactory(new PropertyValueFactory<>("dob"));
            colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

            refreshTable();

            txtSearch.textProperty().addListener((observableValue, previous, current) -> {
                if (!Objects.equals(previous, current)) {
                    tblStudents.getItems().clear();
                    List<StudentTm> collect = studentService.searchStudentByText(current).stream().map(this::toStudentTm).collect(Collectors.toList());
                    tblStudents.setItems(FXCollections.observableArrayList(collect));
                }
            });
        }catch (RuntimeException exception){
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
        }
    }

    private StudentTm toStudentTm(StudentDto studentDto) {
        StudentTm studentTm = new StudentTm();
        studentTm.setStudent_id(studentDto.getStudent_id());
        studentTm.setName(studentDto.getName());
        studentTm.setDob(studentDto.getDob());
        studentTm.setGender(studentDto.getGender());
        studentTm.setAddress(studentDto.getAddress());
        return studentTm;
    }

    private void refreshTable() {
        try {
            generateStudentId();
            List<StudentDto> all = studentService.getAll();
            ObservableList<StudentTm> studentDtoObservableList = FXCollections.observableArrayList();
            all.forEach(dto -> studentDtoObservableList.add(new StudentTm(dto.getStudent_id(), dto.getName(), dto.getAddress(), dto.getContact_no(), dto.getDob(), dto.getGender())));
            tblStudents.setItems(studentDtoObservableList);
        } catch (RuntimeException exception) {
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
            tblStudents.getItems().clear();
        }
    }

    private void generateStudentId() {
        txtId.setText(studentService.getLastId());
    }

    public void tblStudentOnMouseClicked(MouseEvent mouseEvent) {
        StudentTm selectedItem = tblStudents.getSelectionModel().getSelectedItem();
        try {
            if (selectedItem != null) {
                btnUpdate.setDisable(false);
                btnDelete.setDisable(false);
                btnAdd.setDisable(true);
                txtId.setText(selectedItem.getStudent_id());
                txtName.setText(selectedItem.getName());
                txtAddress.setText(selectedItem.getAddress());
                if (selectedItem.getGender().equals("Male")) {
                    rBtnMale.setSelected(true);
                } else {
                    rBtnFemale.setSelected(true);
                }
                txtContact.setText(selectedItem.getContact_no());
                cmbDob.setValue(selectedItem.getDob().toLocalDate());
            } else {
                btnUpdate.setDisable(true);
            }
        } catch (RuntimeException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
}
