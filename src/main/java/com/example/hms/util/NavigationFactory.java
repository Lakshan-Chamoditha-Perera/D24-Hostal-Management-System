package com.example.hms.util;

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
                stage.setTitle("D/24 Hostal Management System");
                init("LoginPage.fxml");
                break;

           /* case DASHBOARD:
                stage.setTitle("Dashboard");
                init("");
                break;*/

            default:
                throw new RuntimeException("Ui not Implemented!");
        }
    }

    private void init(String fileName) throws IOException {
        panel.getChildren().add(FXMLLoader.load(getClass().getResource("../view/" + fileName)));
    }
}
