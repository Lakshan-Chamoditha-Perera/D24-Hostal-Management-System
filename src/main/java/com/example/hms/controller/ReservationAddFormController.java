package com.example.hms.controller;

import com.example.hms.dto.ReservationDto;
import com.example.hms.dto.RoomDto;
import com.example.hms.dto.StudentDto;
import com.example.hms.service.ServiceFactory;
import com.example.hms.service.custom.ReservationService;
import com.example.hms.service.custom.RoomService;
import com.example.hms.service.custom.StudentService;
import com.example.hms.service.util.ServiceType;
import com.example.hms.util.FactoryConfiguration;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ReservationAddFormController implements Initializable {

    public Label lblRoomPrice;
    public Label lblReservationId;
    @FXML
    private AnchorPane floatingPane;

    @FXML
    private ComboBox<String> cmbRoomId;

    @FXML
    private ComboBox<String> cmbStdId;

    @FXML
    private Label txtRoomAvailableQty;

    @FXML
    private JFXDatePicker txtDate;

    @FXML
    private JFXButton btnPayNow;

    @FXML
    private JFXButton btnBook;
    private StudentService studentService;
    private RoomService roomService;
    private ReservationService reservationService;

    @FXML
    void btnBookOnAction(ActionEvent event) {
        saveReservation("un-paid");
    }

    @FXML
    void btnPayNowOnAction(ActionEvent event) {
        saveReservation("paid");
    }

    private void saveReservation(String status) {
        try {
            if (validateData()) {
                ReservationDto reservationDto = new ReservationDto();
                reservationDto.setRes_id(lblReservationId.getText());
                reservationDto.setStatus(status);
                reservationDto.setDate(Date.valueOf(txtDate.getValue()));

                StudentDto studentDto = studentService.view(cmbStdId.getValue());
                reservationDto.setStudentDto(studentDto);

                RoomDto dto = roomService.view(cmbRoomId.getValue());
                dto.setQty(dto.getQty() - 1);
                reservationDto.setRoomDto(dto);

                reservationService.save(reservationDto);
                //System.out.println(session.isConnected());

                Stage stage = (Stage) floatingPane.getScene().getWindow();
                stage.setAlwaysOnTop(false);
                new Alert(Alert.AlertType.INFORMATION, "Added!").showAndWait();
                stage.setAlwaysOnTop(true);
                stage.close();
            }
        } catch (RuntimeException exception) {
            Stage stage = (Stage) floatingPane.getScene().getWindow();
            stage.setAlwaysOnTop(false);
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).showAndWait();
            stage.setAlwaysOnTop(false);
        }
    }

    private boolean validateData() throws RuntimeException {
        if (cmbStdId.getSelectionModel().getSelectedItem() != null) {
            if (cmbRoomId.getSelectionModel().getSelectedItem() != null) {
                if (txtDate.getValue() != null) {
                    if (!txtDate.getValue().isBefore(LocalDate.now())) {
                        System.out.println("Validation Done");
                        return true;
                    }
                    throw new RuntimeException("Date must be after today");
                }
                throw new RuntimeException("Select Date");
            }
            throw new RuntimeException("Select Room");
        }
        throw new RuntimeException("Select Student");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        studentService = ServiceFactory.getServiceFactory().getService(ServiceType.StudentService);
        roomService = ServiceFactory.getServiceFactory().getService(ServiceType.RoomService);
        reservationService = ServiceFactory.getServiceFactory().getService(ServiceType.ReservationService);

        setReservationId();
        setStudentList();
        setRoomList();
    }

    private void setRoomList() {
        try {
            List<RoomDto> roomDtoList = roomService.getAll();
            ObservableList<String> roomIdObservableList = FXCollections.observableArrayList();
            for (RoomDto room : roomDtoList) roomIdObservableList.add(room.getRoom_type_id());
            cmbRoomId.setItems(roomIdObservableList);
        } catch (RuntimeException ignored) {
//
        }
    }

    private void setStudentList() {
        try {
            List<StudentDto> studentDtoList = studentService.getAll();
            ObservableList<String> studentIdObservableList = FXCollections.observableArrayList();
            for (StudentDto studentDto : studentDtoList) studentIdObservableList.add(studentDto.getStudent_id());
            cmbStdId.setItems(studentIdObservableList);
        } catch (RuntimeException ignored) {
//
        }
    }

    private void setReservationId() {
        lblReservationId.setText(reservationService.getLastId());
    }

    public void cmbRoomIdOnAction(ActionEvent actionEvent) {
        try {
            String selectedItem = cmbRoomId.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                RoomDto room = roomService.view(selectedItem);
                lblRoomPrice.setText(String.valueOf(room.getKey_money()));
                txtRoomAvailableQty.setText(String.valueOf(room.getQty()));

                if (room.getQty() != 0) {
                    btnPayNow.setDisable(false);
                    btnBook.setDisable(false);
                } else {
                    btnPayNow.setDisable(true);
                    btnBook.setDisable(true);
                    throw new RuntimeException("Room not available at the moment!");
                }
            } else {
                lblRoomPrice.setText("0");
                txtRoomAvailableQty.setText("0");
            }
        } catch (RuntimeException exception) {
            Stage stage = (Stage) floatingPane.getScene().getWindow();
            stage.setAlwaysOnTop(false);
            new Alert(Alert.AlertType.ERROR, exception.getMessage()).showAndWait();
            stage.setAlwaysOnTop(false);
        }
    }

    public void cmbStudentIdOnAction(ActionEvent actionEvent) {
       /* try {
            String selectedItem = cmbStdId.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                StudentDto studentDto = new StudentDto();
                studentDto.setStudent_id(selectedItem);
                StudentDto room = studentService.view(studentDto, FactoryConfiguration.getFactoryConfiguration().getSession());
            } else {
            }
        }catch (RuntimeException exception){
        }*/
    }

}
