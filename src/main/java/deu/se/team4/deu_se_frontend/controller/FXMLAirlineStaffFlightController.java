/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deu.se.team4.deu_se_frontend.controller;

import deu.se.team4.deu_se_frontend.model.APICenter;
import deu.se.team4.deu_se_frontend.vo.FlightVO;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author 강호동
 */
public class FXMLAirlineStaffFlightController implements Initializable {

    @FXML
    TableView<FlightVO> flightTable;
    @FXML
    TableColumn<FlightVO, String> colAirline = new TableColumn<>("항공사");
    @FXML
    TableColumn<FlightVO, String> colInitial = new TableColumn<>("편명");
    @FXML
    TableColumn<FlightVO, String> colStartDate = new TableColumn<>("출발 시각");
    @FXML
    TableColumn<FlightVO, String> colEndDate = new TableColumn<>("도착 시각");
    @FXML
    TableColumn<FlightVO, String> colStartAirport = new TableColumn<>("출발지");
    @FXML
    TableColumn<FlightVO, String> colDestAirport = new TableColumn<>("도착지");
    @FXML
    Label start_airport;
    @FXML
    Label dest_airport;
    @FXML
    Label via;
    @FXML
    Label identifier;
    @FXML
    Label start_date;
    @FXML
    Label end_date;
    @FXML
    Label airline_kor;
    @FXML
    Label first_seat_num;
    @FXML
    Label business_seat_num;
    @FXML
    Label economy_seat_num;
    @FXML
    Label sign;
    @FXML
    Button addflightbtn;
    @FXML
    AnchorPane flightInformation;
    @FXML
    AnchorPane addflightinformation;
    @FXML
    Label submitflight;
    @FXML
    DatePicker add_start_date;
    @FXML
    TextField add_start_time;
    @FXML
    DatePicker add_end_date;
    @FXML
    TextField add_end_time;
    @FXML
    TextField add_identifier;
    @FXML
    TextField add_airplane_name;
    @FXML
    CheckBox add_via;
    @FXML
    TextField add_start_airport;
    @FXML
    TextField add_dest_airport;
    @FXML
    TextField add_first_seat;
    @FXML
    TextField add_economy_seat;
    @FXML
    TextField add_business_seat;
    @FXML
    Label left_btn;
    @FXML
    Label right_btn;
    @FXML
    Label date_flight;

    String today_date;
    int month;
    Calendar cal;
    DateFormat df;

    ObservableList<FlightVO> observableList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cal = Calendar.getInstance();
        cal.setTime(new Date());
        df = new SimpleDateFormat("yyyy-MM-dd");
        today_date = df.format(cal.getTime());
        month = cal.get(Calendar.MONTH) + 1;
        date_flight.setText(cal.get(Calendar.YEAR) + "년 " + month + "월 " + cal.get(Calendar.DATE) + "일 실시간 항공편");
        
        flightTable.setOnMouseClicked(e -> flightTableEvent(e));
        addflightbtn.setOnMouseClicked(e -> InputFlightInformation(e));
        submitflight.setOnMouseClicked(e -> runAddFlight(e));
        colAirline.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().airline_kor));
        colInitial.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().identifier));
        colStartDate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().start_date));
        colEndDate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().end_date));
        colStartAirport.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().start_airport));
        colDestAirport.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().dest_airport));
        left_btn.setOnMouseClicked(e -> minusDate(e));
        right_btn.setOnMouseClicked(e -> plusDate(e));

        loadFlight();

        try {
            setFlightInformation(flightTable.getItems().get(0));

        } catch (IndexOutOfBoundsException e) {
            System.out.println("항공편이 존재하지 않습니다");
        }
    }

    private void plusDate(MouseEvent e) {
        month = cal.get(Calendar.MONTH) + 1;
        cal.add(Calendar.DATE, +1);
        today_date = df.format(cal.getTime());
        date_flight.setText(cal.get(Calendar.YEAR) + "년 " + month + "월 " + cal.get(Calendar.DATE) + "일 실시간 항공편");
        loadFlight();
        try {
            setFlightInformation(flightTable.getItems().get(0));

        } catch (IndexOutOfBoundsException e2) {
            System.out.println("항공편이 존재하지 않습니다");
        }

    }

    private void minusDate(MouseEvent e) {
        month = cal.get(Calendar.MONTH) + 1;
        cal.add(Calendar.DATE, -1);
        today_date = df.format(cal.getTime());
        date_flight.setText(cal.get(Calendar.YEAR) + "년 " + month + "월 " + cal.get(Calendar.DATE) + "일 실시간 항공편");
        loadFlight();
        try {
            setFlightInformation(flightTable.getItems().get(0));

        } catch (IndexOutOfBoundsException e3) {
            System.out.println("항공편이 존재하지 않습니다");
        }

    }

    private void setFlightInformation(FlightVO flight_vo) {

        flightInformation.setVisible(true);
        addflightinformation.setVisible(false);
        identifier.setText(flight_vo.identifier);
        start_airport.setText(flight_vo.start_airport);
        airline_kor.setText(flight_vo.airline_kor);
        dest_airport.setText(flight_vo.dest_airport);
        start_date.setText(flight_vo.start_date);
        end_date.setText(flight_vo.end_date);

        first_seat_num.setText(Integer.toString(flight_vo.first_seat_num));
        business_seat_num.setText(Integer.toString(flight_vo.business_seat_num));
        economy_seat_num.setText(Integer.toString(flight_vo.economy_seat_num));

        if (flight_vo.sign == 1) {
            sign.setText("승인");
        } else {
            sign.setText("미승인");
        }

        if (flight_vo.via == 1) {
            via.setVisible(true);
        } else {
            via.setVisible(false);
        }

    }

    private void flightTableEvent(MouseEvent e) {
        setFlightInformation(flightTable.getSelectionModel().getSelectedItem());

    }

    private void InputFlightInformation(MouseEvent e) {
        flightInformation.setVisible(false);
        addflightinformation.setVisible(true);

    }

    private void runAddFlight(MouseEvent e) {
        FlightVO flight_vo = new FlightVO();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String start_date = LocalDate.parse(add_start_date.getValue().toString(), dtf) + " " + add_start_time.getText();
        String end_date = LocalDate.parse(add_end_date.getValue().toString(), dtf) + " " + add_end_time.getText();

        flight_vo.airplane_name = add_airplane_name.getText();
        flight_vo.start_airport = add_start_airport.getText();
        flight_vo.dest_airport = add_dest_airport.getText();
        flight_vo.via = add_via.isSelected() ? 1 : 0;
        flight_vo.identifier = add_identifier.getText();
        flight_vo.start_date = start_date;
        flight_vo.end_date = end_date;
        flight_vo.first_seat_num = Integer.parseInt(add_first_seat.getText());
        flight_vo.business_seat_num = Integer.parseInt(add_business_seat.getText());
        flight_vo.economy_seat_num = Integer.parseInt(add_economy_seat.getText());
        flight_vo.sign = 1;

        if (APICenter.getInstance().addFlight(flight_vo)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("A-INFO");
            alert.setHeaderText("승인요청 완료");
            alert.setContentText("항공편 편성이 정상적으로 요청되었습니다");
            alert.showAndWait();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("A-INFO");
            alert.setHeaderText("승인요청 실패");
            alert.setContentText("알 수 없는 오류로 인해 편성이 반려되었습니다.");
            alert.showAndWait();

        }
        loadFlight();
    }

    private void loadFlight() {
        flightTable.getItems().clear();
        observableList = FXCollections.observableArrayList(APICenter.getInstance().getFlightByDate(today_date));
        flightTable.setItems(observableList);
    }
}
