/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deu.se.team4.deu_se_frontend.controller;

import com.google.gson.JsonElement;
import deu.se.team4.deu_se_frontend.model.APICenter;
import deu.se.team4.deu_se_frontend.vo.BookVO;
import deu.se.team4.deu_se_frontend.vo.FlightVO;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
public class FXMLCustomerFlightController implements Initializable {

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
    Button addbookbtn;
    @FXML
    AnchorPane flightInformation;
    @FXML
    AnchorPane addflightinformation;
    @FXML
    Label submitbook;
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
    Label right_btn;
    @FXML
    Label date_flight;
    @FXML
    Label firstbtn;
    @FXML
    Label economybtn;
    @FXML
    Label businessbtn;
    @FXML
    TextField baggagekg;
    @FXML
    TextField car_number;

    String today_date;
    String seat_grade;
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
        addbookbtn.setOnMouseClicked(e -> InputFlightInformation(e));
        submitbook.setOnMouseClicked(e -> runbookFlight(e));
        colAirline.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().airline_kor));
        colInitial.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().identifier));
        colStartDate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().start_date));
        colEndDate.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().end_date));
        colStartAirport.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().start_airport));
        colDestAirport.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().dest_airport));
        right_btn.setOnMouseClicked(e -> plusDate(e));
        firstbtn.setOnMouseClicked(e -> clickFirstBtn(e));
        businessbtn.setOnMouseClicked(e -> clickBusinessBtn(e));
        economybtn.setOnMouseClicked(e -> clickEconomyBtn(e));
        seat_grade = "F";
        addbookbtn.setDisable(true);

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

    private void setFlightInformation(FlightVO flight_vo) {

        JsonElement element = APICenter.getInstance().countCanBook(flight_vo.identifier);

        flightInformation.setVisible(true);
        addflightinformation.setVisible(false);
        identifier.setText(flight_vo.identifier);
        start_airport.setText(flight_vo.start_airport);
        airline_kor.setText(flight_vo.airline_kor);
        dest_airport.setText(flight_vo.dest_airport);
        start_date.setText(flight_vo.start_date);
        end_date.setText(flight_vo.end_date);
        first_seat_num.setText(element.getAsJsonObject().get("first_seat_num").getAsString());
        business_seat_num.setText(element.getAsJsonObject().get("business_seat_num").getAsString());
        economy_seat_num.setText(element.getAsJsonObject().get("economy_seat_num").getAsString());

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
        addbookbtn.setDisable(false);
        setFlightInformation(flightTable.getSelectionModel().getSelectedItem());

    }

    private void InputFlightInformation(MouseEvent e) {
        if (flightTable.isVisible()) {
            right_btn.setVisible(false);
            date_flight.setVisible(false);
            flightTable.setVisible(false);
            addflightinformation.setVisible(true);
        } else {
            date_flight.setVisible(true);
            flightTable.setVisible(true);
            right_btn.setVisible(true);
            addflightinformation.setVisible(false);
        }
    }

    private void runbookFlight(MouseEvent e) {

        BookVO book_vo = new BookVO();
        book_vo.identifier = identifier.getText();
        book_vo.seat_grade = seat_grade;
        book_vo.baggage_weight = Integer.parseInt(baggagekg.getText());
        book_vo.car_number = car_number.getText();

        if (APICenter.getInstance().bookFlight(book_vo)) {
            JsonElement element = APICenter.getInstance().countCanBook(identifier.getText());
            first_seat_num.setText(element.getAsJsonObject().get("first_seat_num").getAsString());
            business_seat_num.setText(element.getAsJsonObject().get("business_seat_num").getAsString());
            economy_seat_num.setText(element.getAsJsonObject().get("economy_seat_num").getAsString());
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("A-INFO");
            alert.setHeaderText("예약 성공");
            alert.setContentText("내 여행 일정으로 이동해 좌석을 확인해주세요 !");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("A-INFO");
            alert.setHeaderText("예약 실패");
            alert.setContentText("좌석 예약에 실패했습니다.");
            alert.showAndWait();

        }

        //loadFlight();
    }

    private void loadFlight() {
        flightTable.getItems().clear();
        observableList = FXCollections.observableArrayList(APICenter.getInstance().getFlightByDate(today_date));
        flightTable.setItems(observableList);
    }

    private void clickEconomyBtn(MouseEvent e) {
        firstbtn.setOpacity(0.5);
        economybtn.setOpacity(1.0);
        businessbtn.setOpacity(0.5);
        seat_grade = "E";
    }

    private void clickBusinessBtn(MouseEvent e) {
        firstbtn.setOpacity(0.5);
        economybtn.setOpacity(0.5);
        businessbtn.setOpacity(1);
        seat_grade = "B";
    }

    private void clickFirstBtn(MouseEvent e) {
        firstbtn.setOpacity(1);
        economybtn.setOpacity(0.5);
        businessbtn.setOpacity(0.5);
        seat_grade = "F";
    }

}
