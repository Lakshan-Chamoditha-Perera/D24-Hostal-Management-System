package com.example.hms.controller;

import com.example.hms.util.NavigationFactory;
import com.example.hms.util.navigation.NavigationType;
import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

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
    void btnReservationOnAction(ActionEvent event) throws IOException {
        NavigationFactory.getInstance().navigate(NavigationType.RESERVATION,pane);
    }

    @FXML
    void btnRoomsOnAction(ActionEvent event) throws IOException {
        NavigationFactory.getInstance().navigate(NavigationType.ROOM,pane);
    }

    @FXML
    void btnStudentsOnAction(ActionEvent event) throws IOException {
        NavigationFactory.getInstance().navigate(NavigationType.STUDENT,pane);
    }

    @FXML
    void btnUserOnAction(ActionEvent event) throws IOException {
        NavigationFactory.getInstance().navigate(NavigationType.USER,pane);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0),
                event -> lblTime.setText(""+new SimpleDateFormat("EEEE - MMM-dd-yyyy  HH:mm:ss").format(Calendar.getInstance().getTime()))),
                new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
}
