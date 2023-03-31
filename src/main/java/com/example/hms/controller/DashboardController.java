package com.example.hms.controller;

import com.example.hms.util.NavigationFactory;
import com.example.hms.util.navigation.NavigationType;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DashboardController {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private AnchorPane sidePane;

    @FXML
    private JFXButton btnLogout;

    @FXML
    private JFXButton btnStudents;

    @FXML
    private JFXButton btnRooms;

    @FXML
    private JFXButton btnReservation;

    @FXML
    private JFXButton btnUser;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label lblTime;

    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException {
        NavigationFactory.getInstance().navigate(NavigationType.LOGIN,mainPane);
    }

    @FXML
    void btnReservationOnAction(ActionEvent event) {

    }

    @FXML
    void btnRoomsOnAction(ActionEvent event) throws IOException {
        NavigationFactory.getInstance().navigate(NavigationType.ROOM,pane);
    }

    @FXML
    void btnStudentsOnAction(ActionEvent event) {

    }

    @FXML
    void btnUserOnAction(ActionEvent event) {

    }

}
