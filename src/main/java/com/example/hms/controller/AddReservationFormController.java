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
import com.example.hms.util.regex.RegExFactory;
import com.example.hms.util.regex.RegExType;
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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class AddReservationFormController implements Initializable {

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
    private TextField txtQty;

    @FXML
    private JFXDatePicker txtDate;

    @FXML
    private Label txtTotal;

    @FXML
    private JFXButton btnPayNow;

    @FXML
    private JFXButton btnBook;
    private StudentService studentService;
    private RoomService roomService;
    private ReservationService reservationService;

    @FXML
    void btnBookOnAction(ActionEvent event) {

    }

    @FXML
    void btnPayNowOnAction(ActionEvent event) {
        saveReservation("Paid");
    }

    private void saveReservation(String status) {
        try {
            if (validateData()) {
                ReservationDto reservationDto = new ReservationDto();
                reservationDto.setRes_id(lblReservationId.getText());
                reservationDto.setStatus(status);
                reservationDto.setDate(Date.valueOf(txtDate.getValue()));

                StudentDto studentDto = new StudentDto();
                studentDto.setStudent_id(cmbStdId.getValue());
                reservationDto.setStudentDto(studentDto);

                RoomDto roomDto = new RoomDto();
                roomDto.setRoom_type_id(cmbRoomId.getValue());
                reservationDto.setRoomDto(roomDto);

                Boolean save = reservationService.save(reservationDto, FactoryConfiguration.getFactoryConfiguration().getSession());
                new Alert(Alert.AlertType.INFORMATION,"Added!").show();

            }
        } catch (RuntimeException exception) {
            exception.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, exception.getMessage());
            alert.showAndWait();
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
            List<RoomDto> roomDtoList = roomService.getAll(FactoryConfiguration.getFactoryConfiguration().getSession());
            ObservableList<String> roomIdObservableList = FXCollections.observableArrayList();
            for (RoomDto room : roomDtoList) roomIdObservableList.add(room.getRoom_type_id());
            cmbRoomId.setItems(roomIdObservableList);
        } catch (RuntimeException ignored) {
//
        }
    }

    private void setStudentList() {
        try {
            List<StudentDto> studentDtoList = studentService.getAll(FactoryConfiguration.getFactoryConfiguration().getSession());
            ObservableList<String> studentIdObservableList = FXCollections.observableArrayList();
            for (StudentDto studentDto : studentDtoList) studentIdObservableList.add(studentDto.getStudent_id());
            cmbStdId.setItems(studentIdObservableList);
        } catch (RuntimeException ignored) {
//
        }
    }

    private void setReservationId() {
        lblReservationId.setText(reservationService.getLastId(FactoryConfiguration.getFactoryConfiguration().getSession()));
    }

    public void cmbRoomIdOnAction(ActionEvent actionEvent) {
        try {
            String selectedItem = cmbRoomId.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                RoomDto roomDto = new RoomDto();
                roomDto.setRoom_type_id(selectedItem);
                RoomDto room = roomService.view(roomDto, FactoryConfiguration.getFactoryConfiguration().getSession());
                lblRoomPrice.setText(String.valueOf(room.getKey_money()));
                txtRoomAvailableQty.setText(String.valueOf(room.getQty()));
                if (room.getQty() != 0) {
                    txtQty.setDisable(false);
                    btnPayNow.setDisable(false);
                    btnBook.setDisable(false);
                } else {
                    btnPayNow.setDisable(true);
                    btnBook.setDisable(true);
                    txtQty.setDisable(true);
                    throw new RuntimeException("Room not available at the moment!");
                }
            } else {
                lblRoomPrice.setText("0");
                txtRoomAvailableQty.setText("0");
            }
        } catch (RuntimeException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR, exception.getMessage());
            alert.setResizable(true);
            alert.showAndWait();
            txtQty.clear();
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

    public void txtQtyOnKeyReleased(KeyEvent keyEvent) {
        try {
            if (RegExFactory.getInstance().getPattern(RegExType.INTEGER).matcher(txtQty.getText()).matches() & Integer.parseInt(txtQty.getText()) > 0) {
                if (Integer.parseInt(txtQty.getText()) <= Integer.parseInt(txtRoomAvailableQty.getText())) {
                    double price = Integer.parseInt(txtQty.getText()) * Double.parseDouble(lblRoomPrice.getText());
                    txtTotal.setText(String.valueOf(price));
                    btnBook.setDisable(false);
                    btnPayNow.setDisable(false);
                } else {
                    throw new RuntimeException("Qty must be lower than " + txtRoomAvailableQty.getText());
                }
            } else {
                throw new RuntimeException("Invalid input");
            }
        } catch (RuntimeException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR, exception.getMessage());
            alert.setResizable(true);
            alert.showAndWait();
            txtQty.clear();
        }
    }
}
