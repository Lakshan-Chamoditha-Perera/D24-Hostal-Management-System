package com.example.hms.controller;

import com.example.hms.util.NavigationFactory;
import com.example.hms.util.navigation.NavigationType;
import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    private final KeyCombination controlS = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
    private final KeyCombination controlR = new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN);
    private final KeyCombination controlE = new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN);
    private final KeyCombination controlL = new KeyCodeCombination(KeyCode.L, KeyCombination.CONTROL_DOWN);
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
    void btnLogoutOnAction() throws IOException {
        NavigationFactory.getInstance().navigate(NavigationType.LOGIN, mainPane);
    }

    @FXML
    void btnReservationOnAction(ActionEvent event) throws IOException {
        NavigationFactory.getInstance().navigate(NavigationType.RESERVATION, pane);

    }

    @FXML
    void btnRoomsOnAction(ActionEvent event) throws IOException {
        NavigationFactory.getInstance().navigate(NavigationType.ROOM, pane);
    }

    @FXML
    void btnStudentsOnAction(ActionEvent event) throws IOException {
        NavigationFactory.getInstance().navigate(NavigationType.STUDENT, pane);
    }

    @FXML
    void btnUserOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/com/example/hms/view/UserCredentialForm.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        Parent parent = fxmlLoader.load();
        UserCredentialFormController controller = fxmlLoader.getController();
        controller.init(this);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setAlwaysOnTop(true);
        stage.centerOnScreen();
        stage.setScene(new Scene(parent));
        mainPane.setDisable(true);
        stage.showAndWait();
        mainPane.setDisable(false);
    }

    public void navigate() throws IOException {
        NavigationFactory.getInstance().navigate(NavigationType.USER, pane);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0), event -> lblTime.setText("" + new SimpleDateFormat("EEEE - MMM-dd-yyyy  HH:mm:ss").format(Calendar.getInstance().getTime()))), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
//        sidePane.setOnKeyPressed(event -> {
//            if (event.getCode() == KeyCode.S && event.isControlDown()) {
//                btnStudents.fire();
//            } else if (event.getCode() == KeyCode.R && event.isControlDown()) {
//                btnRooms.fire();
//            } else if (event.getCode() == KeyCode.E && event.isControlDown()) {
//                btnReservation.fire();
//            } else if (event.getCode() == KeyCode.L && event.isControlDown()) {
//                btnLogout.fire();
//            }
//        });
        sidePane.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case S:
                    if (controlS.match(event)) btnStudents.fire();break;
                case R:
                    if (controlR.match(event)) btnRooms.fire();break;
                case E:
                    if (controlE.match(event)) btnReservation.fire();break;
                case L:
                    if (controlL.match(event)) btnLogout.fire() ;break;
                default: break;
            }
        }); //shortcut keys
    }


}
