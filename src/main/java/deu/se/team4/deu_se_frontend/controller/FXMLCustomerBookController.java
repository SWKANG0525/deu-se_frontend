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
public class FXMLCustomerBookController implements Initializable {

    @FXML
    TableView<BookVO> bookTable;

    @FXML
    TableColumn<BookVO, String> colInitial = new TableColumn<>("편명");
    @FXML
    Label identifier;
    @FXML
    Label start_date;
    @FXML
    Label end_date;
    @FXML
    Label start_airport;
    @FXML
    Label dest_airport;
    @FXML
    Label car_number;
    @FXML
    Label park_pos;
    @FXML
    Label seat_num;
    @FXML
    Label seat_grade;
    @FXML
    Label baggage_kg;
    @FXML
    Button cancelbtn;

    ObservableList<BookVO> observableBookList;
    int book_id;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bookTable.setOnMouseClicked(e -> bookTableEvent(e));
        colInitial.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().identifier));
        cancelbtn.setOnMouseClicked(e -> deleteBookEvent(e));
        loadBook();
        cancelbtn.setDisable(true);
        cancelbtn.setOpacity(0.5);
        try {
            setBookInformation(bookTable.getItems().get(0));

        } catch (IndexOutOfBoundsException e) {
            System.out.println("항공편이 존재하지 않습니다");
        }

    }

    private void loadBook() {
        bookTable.getItems().clear();
        observableBookList = FXCollections.observableArrayList(APICenter.getInstance().getBookByCustomerId());
        bookTable.setItems(observableBookList);
    }

    private void bookTableEvent(MouseEvent e) {
        setBookInformation(bookTable.getSelectionModel().getSelectedItem());
        cancelbtn.setDisable(false);
        cancelbtn.setOpacity(1);
        book_id = bookTable.getSelectionModel().getSelectedItem().id;
    }

    private void setBookInformation(BookVO book_vo) {
        FlightVO flight_vo = APICenter.getInstance().getFlightByIdentifier(book_vo.identifier);
        System.out.println(flight_vo.start_date);
        identifier.setText(book_vo.identifier);
        start_date.setText(flight_vo.start_date);
        end_date.setText(flight_vo.end_date);
        start_airport.setText(flight_vo.start_airport);
        dest_airport.setText(flight_vo.dest_airport);
        car_number.setText(book_vo.car_number);
        park_pos.setText("A4");
        seat_num.setText(Integer.toString(book_vo.seat_num));
        seat_grade.setText(book_vo.seat_grade);
        baggage_kg.setText(Integer.toString(book_vo.baggage_weight));

    }
    
        private void deleteBookEvent(MouseEvent e) {
        if (APICenter.getInstance().requestBookDelete(book_id)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("A-INFO");
            alert.setHeaderText("예약취소 완료");
            alert.setContentText("항공권 취소가 정상 처리되었습니다");
            alert.showAndWait();
            loadBook();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("A-INFO");
            alert.setHeaderText("취소요청 실패");
            alert.setContentText("알 수 없는 오류로 인해 예약 취소가 반려되었습니다.");
            alert.showAndWait();

        }

    }

}
