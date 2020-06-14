/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deu.se.team4.deu_se_frontend.controller;

import com.sun.prism.paint.Paint;
import deu.se.team4.deu_se_frontend.ParkComponent;
import deu.se.team4.deu_se_frontend.model.ParkModel;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 *
 * @author 강호동
 */
public class FXMLAirlineStaffParkController implements Initializable {

    @FXML
    GridPane grid;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ParkModel park_model = new ParkModel();
        setParkGUI(park_model.makeParkList(0));
    }

    public void setParkGUI(ParkComponent park_list) {
        Iterator<ParkComponent> iterator = park_list.createIterator();
        while (iterator.hasNext()) {
            ParkComponent parkComponent = iterator.next();
            try {
                generateParkPos(parkComponent);
            } catch (NullPointerException e) {
                System.out.println("Not Leaf Node");
            }
        }

    }

    public Label generateParkPos(ParkComponent parkComponent) {

        try {
            Label label = new Label(parkComponent.getCarNumber());
            label.setStyle("-fx-font-family: NanumSquare Bold; -fx-font-size : 20px;");
            label.setTextFill(Color.WHITE);
            label.setAlignment(Pos.CENTER);

            if ("A".equals(parkComponent.getParkArea())) {
                grid.add(label, 0, parkComponent.getParkPos(), 1, 1);

            } else if ("B".equals(parkComponent.getParkArea())) {
                grid.add(label, 1, parkComponent.getParkPos(), 1, 1);

            } else if ("C".equals(parkComponent.getParkArea())) {
                grid.add(label, 2, parkComponent.getParkPos(), 1, 1);
                return label;

            }
            grid.setAlignment(Pos.CENTER);

        } catch (UnsupportedOperationException e) {
            System.out.println("Not Leaf Node.");

        }
        return null;

    }

}
