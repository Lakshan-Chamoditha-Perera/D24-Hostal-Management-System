package com.example.hms.controller;

import com.example.hms.util.NavigationFactory;
import com.example.hms.util.navigation.NavigationType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UnpaidCaseFormController implements Initializable {

    public TableView tblStudents;
    @FXML
    private AnchorPane pane;


    @FXML
    private TableColumn<?, ?> colStdId;

    @FXML
    private TableColumn<?, ?> colStdName;

    @FXML
    private TableColumn<?, ?> colStdAddress;

    @FXML
    private TableColumn<?, ?> colMobile;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        NavigationFactory.getInstance().navigate(NavigationType.RESERVATION, pane);
    }

    public void tblStudentsOnMouseClicked(MouseEvent mouseEvent) {

    }
}
