package com.example.hms.controller;

import com.example.hms.dto.ReservationDto;
import com.example.hms.dto.RoomDto;
import com.example.hms.service.ServiceFactory;
import com.example.hms.service.custom.ReservationService;
import com.example.hms.service.custom.RoomService;
import com.example.hms.service.util.ServiceType;
import com.example.hms.tm.ReservationTm;
import com.example.hms.util.FactoryConfiguration;
import com.example.hms.util.NavigationFactory;
import com.example.hms.util.navigation.NavigationType;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ReservationFormController implements Initializable {

    public JFXButton btnMarkAsPaid;
    public JFXButton btnViewUnpaidStudent;
    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<ReservationTm> tblReservations;

    @FXML
    private TableColumn<ReservationTm, String> colReservationId;

    @FXML
    private TableColumn<ReservationTm, String> colStudentId;

    @FXML
    private TableColumn<ReservationTm, Date> colDate;

    @FXML
    private TableColumn<ReservationTm, String> colRoomType;

    @FXML
    private TableColumn<ReservationTm, String> colStatus;

    @FXML
    private TableView<RoomDto> tblRoom;

    @FXML
    private TableColumn<RoomDto, String> colRoomID;

    @FXML
    private TableColumn<RoomDto, String> colType;

    @FXML
    private TableColumn<RoomDto, Double> colKeyMoney;

    @FXML
    private TableColumn<RoomDto, Integer> colQty;

    @FXML
    private JFXButton btnAddReservation;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContactNumber;

    @FXML
    private TextField txtGender;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;
    private RoomService roomService;
    private ReservationService reservationService;

    @FXML
    void btnAddReservationOnAction(ActionEvent event) throws IOException {
        try {
            pane.setDisable(true);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setAlwaysOnTop(true);
            stage.requestFocus();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/AddReservationForm.fxml"))));
            stage.setTitle("Booking");
            stage.showAndWait();
            pane.setDisable(false);

            refreshRoomTable();
            refreshReservationTable();
        } catch (RuntimeException exception) {
            new Alert(Alert.AlertType.INFORMATION, exception.getMessage()).show();

        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        ReservationTm reservationTm = tblReservations.getSelectionModel().getSelectedItem();
        if (reservationTm != null) {
            btnDelete.setDisable(false);
            ReservationDto reservationDto = new ReservationDto();
            reservationDto.setRes_id(reservationTm.getRes_id());
//            reservationService.delete(reservationDto);
            //   new Alert(Alert.AlertType.ERROR, "Reservation Deleted").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Select Item First").show();
        }
        btnDelete.setDisable(true);

    }

    @FXML
    void txtSearchByStudentIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchByStudentIdOnKeyReleased(KeyEvent event) {

    }

    public void tblReservationsOnMouseClicked(MouseEvent mouseEvent) {
        ReservationTm reservationTm = tblReservations.getSelectionModel().getSelectedItem();
        if (reservationTm != null) {
            btnDelete.setDisable(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            roomService = ServiceFactory.getServiceFactory().getService(ServiceType.RoomService);
            reservationService = ServiceFactory.getServiceFactory().getService(ServiceType.ReservationService);

            colRoomID.setCellValueFactory(new PropertyValueFactory<>("room_type_id"));
            colType.setCellValueFactory(new PropertyValueFactory<>("type"));
            colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("key_money"));
            colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

            colReservationId.setCellValueFactory(new PropertyValueFactory<>("res_id"));
            colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
            colStudentId.setCellValueFactory(new PropertyValueFactory<>("student_id"));
            colRoomType.setCellValueFactory(new PropertyValueFactory<>("room_id"));

            refreshRoomTable();
            refreshReservationTable();
        } catch (RuntimeException exception) {
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
        }
    }

    private void refreshReservationTable() throws RuntimeException {
        List<ReservationDto> all = reservationService.getAll();
        ObservableList<ReservationTm> observableList = FXCollections.observableArrayList();
        all.stream().map(dto -> observableList.add(new ReservationTm(dto.getRes_id(), dto.getDate(), dto.getStatus(), dto.getStudentDto().getStudent_id(), dto.getRoomDto().getRoom_type_id()))).collect(Collectors.toList());
        tblReservations.setItems(observableList);
    }

    private void refreshRoomTable() throws RuntimeException {
        List<RoomDto> all = roomService.getAll();
        ObservableList<RoomDto> roomObservableList = FXCollections.observableArrayList();
        roomObservableList.addAll(all);
        tblRoom.setItems(roomObservableList);
    }

    public void btnMarkAsPaidOnAction(ActionEvent actionEvent) {
        try {
            ReservationTm selectedItem = tblReservations.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                ReservationDto reservationDto = new ReservationDto();
                reservationDto.setRes_id(selectedItem.getRes_id());
                ReservationDto dto = reservationService.view(reservationDto);
                dto.setStatus("paid");
                reservationService.update(dto);
                new Alert(Alert.AlertType.INFORMATION, "Payment updated").show();
            }
        } catch (RuntimeException exception) {
            exception.printStackTrace();
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).show();
        }
    }

    public void btnViewUnpaidStudentOnAction(ActionEvent actionEvent) throws IOException {
        NavigationFactory.getInstance().navigate(NavigationType.UNPAID_STUDENTS, pane);
    }
}
