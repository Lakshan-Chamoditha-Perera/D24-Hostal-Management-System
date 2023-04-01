package com.example.hms.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AddReservationFormController {

    @FXML
    private AnchorPane floatingPane;

    @FXML
    private ComboBox<?> cmbRoomId;

    @FXML
    private ComboBox<?> cmbStdId;

    @FXML
    private Label txtRoomAvailableQty;

    @FXML
    private TextField txtQty;

    @FXML
    private JFXDatePicker txtDate;

    @FXML
    private Label txtTotal;

    @FXML
    private JFXButton btnPayNow;

    @FXML
    private JFXButton btnBook;

    @FXML
    void btnBookOnAction(ActionEvent event) {

    }

    @FXML
    void btnPayNowOnAction(ActionEvent event) {

    }

}
