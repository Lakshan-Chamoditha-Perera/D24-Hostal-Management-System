package com.example.hms.controller;

import com.example.hms.dto.RoomDto;
import com.example.hms.service.ServiceFactory;
import com.example.hms.service.custom.RoomService;
import com.example.hms.service.util.ServiceType;
import com.example.hms.util.FactoryConfiguration;
import com.example.hms.util.regex.RegExFactory;
import com.example.hms.util.regex.RegExType;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RoomFormController implements Initializable {

    @FXML
    private TableView<RoomDto> tblRooms;

    @FXML
    private TableColumn<RoomDto, String> colRoomTypeId;

    @FXML
    private TableColumn<RoomDto, String> colRoomType;

    @FXML
    private TableColumn<RoomDto, Double> colKeyMoney;

    @FXML
    private TableColumn<RoomDto, Integer> colQty;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtType;

    @FXML
    private TextField txtKeyMoney;

    @FXML
    private TextField txtQty;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private TextField txtSearch;
    private RoomService roomService;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        try {
            if (validateData()) {
                RoomDto roomDto = new RoomDto();
                roomDto.setRoom_type_id(txtId.getText());
                roomDto.setType(txtType.getText());
                roomDto.setKey_money(Double.valueOf(txtKeyMoney.getText()));
                roomDto.setQty(Integer.valueOf(txtQty.getText()));

                roomService.save(roomDto, FactoryConfiguration.getFactoryConfiguration().getSession());
                new Alert(Alert.AlertType.INFORMATION, "Room Added").show();

                refreshTable();
                clearAll();
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid input!").show();
            }
        } catch (RuntimeException exception) {
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
            clearAll();
        }

    }

    private void clearAll() {
        txtQty.clear();
        txtType.clear();
        txtKeyMoney.clear();
    }

    private boolean validateData() {
        return RegExFactory.getInstance().getPattern(RegExType.DOUBLE).matcher(txtKeyMoney.getText()).matches() && RegExFactory.getInstance().getPattern(RegExType.NAME).matcher(txtType.getText()).matches() && RegExFactory.getInstance().getPattern(RegExType.INTEGER).matcher(txtQty.getText()).matches();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchOnKeyReleased(KeyEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        roomService = ServiceFactory.getServiceFactory().getService(ServiceType.RoomService);

        colRoomTypeId.setCellValueFactory(new PropertyValueFactory<>("room_type_id"));
        colRoomType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("key_money"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        refreshTable();
    }

    private void refreshTable() {
        try {
            txtId.setText(roomService.getLastId(FactoryConfiguration.getFactoryConfiguration().getSession()));
            List all = roomService.getAll(FactoryConfiguration.getFactoryConfiguration().getSession());
            ObservableList<RoomDto> roomObservableList = FXCollections.observableArrayList();
            roomObservableList.addAll(all);
            tblRooms.setItems(roomObservableList);
        } catch (RuntimeException exception) {
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
            tblRooms.getItems().clear();
        }
    }
}
