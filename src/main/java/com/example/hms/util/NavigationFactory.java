package com.example.hms.util;

import animatefx.animation.BounceOutDown;
import animatefx.animation.FadeIn;
import animatefx.animation.Pulse;
import com.example.hms.util.navigation.NavigationType;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class NavigationFactory {
    private static NavigationFactory navigationFactory;
    private static AnchorPane panel;

    private NavigationFactory() {

    }

    public static NavigationFactory getInstance() {
        return navigationFactory == null ? navigationFactory = new NavigationFactory() : navigationFactory;
    }

    public void navigate(NavigationType type, AnchorPane pane) throws IOException, RuntimeException {
        panel = pane;
        Stage stage = (Stage) pane.getScene().getWindow();
        panel.getChildren().clear();
        switch (type) {
            case LOGIN:
                stage.setTitle("D/24 Hostel Management System");
                init("LoginPage.fxml");
                break;

            case DASHBOARD:
                init("Dashboard.fxml");
                break;
            case ROOM:
                stage.setTitle("Manage Rooms");
                init("RoomForm.fxml");
                break;

            case STUDENT:
                stage.setTitle("Manage Students");
                init("StudentForm.fxml");
                break;
            case RESERVATION:
                stage.setTitle("Manage Reservation");
                init("ReservationForm.fxml");
                break;

            case USER:
                stage.setTitle("User");
                init("UserForm.fxml");
                break;

            case UNPAID_STUDENTS:
                stage.setTitle("Un-Paid Cases");
                init("UnpaidCaseForm.fxml");
                break;
            default:
                throw new RuntimeException("Ui not Implemented!");
        }
    }

    private void init(String fileName) throws IOException {
        new FadeIn(panel).play();
        panel.getChildren().add(FXMLLoader.load(getClass().getResource("/com/example/hms/view/" + fileName)));
    }
}
