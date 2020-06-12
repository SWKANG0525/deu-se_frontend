/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deu.se.team4.deu_se_frontend.controller;

import deu.se.team4.deu_se_frontend.model.APICenter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author 강호동
 */
public class FXMLAirlineStaffDashBoardController implements Initializable{
    @FXML
    Label username;
    @FXML
    Label dept;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText(APICenter.getInstance().getAirlineStaffName()+" 승무원");
        dept.setText(APICenter.getInstance().getAirlineKor());
    }
    
}
