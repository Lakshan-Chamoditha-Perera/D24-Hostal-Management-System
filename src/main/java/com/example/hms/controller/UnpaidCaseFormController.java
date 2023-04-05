package com.example.hms.controller;

import com.example.hms.dto.StudentDto;
import com.example.hms.service.ServiceFactory;
import com.example.hms.service.custom.StudentService;
import com.example.hms.service.util.ServiceType;
import com.example.hms.tm.StudentTm;
import com.example.hms.util.FactoryConfiguration;
import com.example.hms.util.NavigationFactory;
import com.example.hms.util.navigation.NavigationType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UnpaidCaseFormController implements Initializable {

    public TableView<StudentTm> tblStudents;
    @FXML
    private AnchorPane pane;


    @FXML
    private TableColumn<StudentTm, String> colStdId;

    @FXML
    private TableColumn<StudentTm, String> colStdName;

    @FXML
    private TableColumn<StudentTm, String> colStdAddress;

    @FXML
    private TableColumn<StudentTm, String> colMobile;


    private StudentService studentService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        studentService = ServiceFactory.getServiceFactory().getService(ServiceType.StudentService);

        colStdId.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colStdName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colMobile.setCellValueFactory(new PropertyValueFactory<>("contact_no"));
        colStdAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        refreshUnpaidStudentTable();
    }

    private void refreshUnpaidStudentTable() {
        try {
            List<StudentDto> list = studentService.getUnpaidStudents(FactoryConfiguration.getFactoryConfiguration().getSession());
            ObservableList<StudentTm> stdTmList = FXCollections.observableArrayList();
            for (StudentDto ele : list) {
                StudentTm studentTm = new StudentTm();
                studentTm.setStudent_id(ele.getStudent_id());
                studentTm.setName(ele.getName());
                studentTm.setAddress(ele.getAddress());
                studentTm.setContact_no(ele.getContact_no());
                stdTmList.add(studentTm);
            }
            tblStudents.setItems(stdTmList);
        } catch (RuntimeException exception) {
            new Alert(Alert.AlertType.INFORMATION, exception.getMessage()).show();
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        NavigationFactory.getInstance().navigate(NavigationType.RESERVATION, pane);
    }

    public void tblStudentsOnMouseClicked(MouseEvent mouseEvent) {

    }
}
