package com.example.hms.controller;

import com.example.hms.util.NavigationFactory;
import com.example.hms.util.navigation.NavigationType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainFormController {

    @FXML
    private AnchorPane pane;

    @FXML
    void onAction(ActionEvent event) throws IOException, RuntimeException {
//        pane.getChildren().clear();
//        pane.getChildren().add(FXMLLoader.load(getClass().getResource("../view/")));
        NavigationFactory.getInstance().navigate(NavigationType.LOGIN,pane);
    }

}
