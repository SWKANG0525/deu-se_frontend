/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deu.se.team4.deu_se_frontend.controller;

import deu.se.team4.deu_se_frontend.model.APICenter;
import deu.se.team4.deu_se_frontend.vo.BookVO;
import deu.se.team4.deu_se_frontend.vo.FlightVO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author 강호동
 */
public class FXMLAirlineStaffBookController implements Initializable {

    @FXML
    TableView<FlightVO> flightTable;
    @FXML
    TableColumn<FlightVO, String> colInitial = new TableColumn<>("편명");
    @FXML
    TableView<BookVO> bookTable;
    @FXML
    TableColumn<BookVO, String> colId = new TableColumn<>("예약 번호");
    @FXML
    TableColumn<BookVO, String> colCustomerId = new TableColumn<>("ID");
    @FXML
    TableColumn<BookVO, String> colSeatNum = new TableColumn<>("좌석 번호");
    @FXML
    TableColumn<BookVO, String> colSeatGrade = new TableColumn<>("좌석 등급");
    @FXML
    TableColumn<BookVO, String> colBaggage = new TableColumn<>("수화물 무게");
    @FXML
    TableColumn<BookVO, String> colCarNumber = new TableColumn<>("차량 번호");
    @FXML
    Label identifier;
    @FXML
    Label start_airport;
    @FXML
    Label dest_airport;
    @FXML
    Label start_date;
    @FXML
    Label end_date;
    @FXML
    Label first_seat_num;
    @FXML
    Label business_seat_num;
    @FXML
    Label economy_seat_num;
    @FXML
    Button delete_book_btn;

    int book_id;

    ObservableList<FlightVO> observableFlightList;
    ObservableList<BookVO> observableBookList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        flightTable.setOnMouseClicked(e -> flightTableEvent(e));
        bookTable.setOnMouseClicked(e -> bookTableEvent(e));
        colInitial.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().identifier));
        colId.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().id)));
        colCustomerId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().customer_id));
        colSeatNum.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().seat_num)));
        colSeatGrade.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().seat_grade));
        colBaggage.setCellValueFactory(cellData -> new SimpleStringProperty(Integer.toString(cellData.getValue().baggage_weight)));
        colCarNumber.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().car_number));
        delete_book_btn.setOnMouseClicked(e -> deleteBookEvent(e));
        loadFlight();
        delete_book_btn.setDisable(true);
        delete_book_btn.setOpacity(0.5);

    }

    private void flightTableEvent(MouseEvent e) {
        setFlightInformation(flightTable.getSelectionModel().getSelectedItem());

    }

    private void setFlightInformation(FlightVO flight_vo) {

        identifier.setText(flight_vo.identifier);
        start_airport.setText(flight_vo.start_airport);
        dest_airport.setText(flight_vo.dest_airport);
        start_date.setText(flight_vo.start_date);
        end_date.setText(flight_vo.end_date);
        first_seat_num.setText(Integer.toString(flight_vo.first_seat_num));
        business_seat_num.setText(Integer.toString(flight_vo.business_seat_num));
        economy_seat_num.setText(Integer.toString(flight_vo.economy_seat_num));
        loadBookList();

    }

    private void loadFlight() {
        flightTable.getItems().clear();
        // 항공사 이름 넣을 시, 편명 조회하는걸로 변경하기
        observableFlightList = FXCollections.observableArrayList(APICenter.getInstance().getFlightByAirlineKor(APICenter.getInstance().getAirlineKor()));
        flightTable.setItems(observableFlightList);
    }

    private void loadBookList() {
        bookTable.getItems().clear();
        observableBookList = FXCollections.observableArrayList(APICenter.getInstance().getBookByIdentifier(identifier.getText()));
        bookTable.setItems(observableBookList);
    }

    private void deleteBookEvent(MouseEvent e) {
        if (APICenter.getInstance().requestBookDelete(book_id)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("A-INFO");
            alert.setHeaderText("예약취소 완료");
            alert.setContentText("항공권 취소가 정상 처리되었습니다");
            alert.showAndWait();
            loadBookList();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("A-INFO");
            alert.setHeaderText("취소요청 실패");
            alert.setContentText("알 수 없는 오류로 인해 예약 취소가 반려되었습니다.");
            alert.showAndWait();

        }

    }

    private void bookTableEvent(MouseEvent e) {
        delete_book_btn.setDisable(false);
        delete_book_btn.setOpacity(1);
        book_id = bookTable.getSelectionModel().getSelectedItem().id;
    }

}
