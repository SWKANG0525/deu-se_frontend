/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deu.se.team4.deu_se_frontend.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

/**
 *
 * @author 강호동
 */
public class FXMLCustomerMainController implements Initializable {

    @FXML
    Label username;
    @FXML
    Label dept;
    @FXML
    Pane PANE1;
    @FXML
    Pane PANE2;
    @FXML
    Pane PANE3;
    @FXML
    Pane PANE4;
    @FXML
    Pane PANE5;
    @FXML
    Label PANE1SUBJECT;
    @FXML
    Label PANE1TIME;
    @FXML
    Label PANE1WHERE;
    @FXML
    Label PANE2SUBJECT;
    @FXML
    Label PANE2TIME;
    @FXML
    Label PANE2WHERE;
    @FXML
    Label PANE3SUBJECT;
    @FXML
    Label PANE3TIME;
    @FXML
    Label PANE3WHERE;
    @FXML
    Label PANE4SUBJECT;
    @FXML
    Label PANE4TIME;
    @FXML
    Label PANE4WHERE;
    @FXML
    Label PANE5SUBJECT;
    @FXML
    Label PANE5TIME;
    @FXML
    Label PANE5WHERE;
    @FXML
    Label btn1;
    @FXML
    Label btn2;
    @FXML
    Label btn3;
    @FXML
    Label btn4;
    @FXML
    Label btn5;
    @FXML
    private MediaView mediaView;
    @FXML
    private AnchorPane rootLayout;
    private AnchorPane mainLayout;
    private AnchorPane flightLayout;
    private AnchorPane bookLayout;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        File file = new File("src/main/resources/assets/background1.mp4");
        Media m = new Media(file.toURI().toString());
        MediaPlayer mp = new MediaPlayer(m);
        mediaView.setMediaPlayer(mp);
        mp.play();
        mp.setCycleCount(MediaPlayer.INDEFINITE);

        try {
            mainLayout = FXMLLoader.load(getClass().getResource("/fxml/FXMLCustomerDashBoardView.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLCustomerMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        rootLayout.getChildren().add(mainLayout);
        btn2.setOnMouseClicked(e -> flightHandle(e));
        btn1.setOnMouseClicked(e -> DashBoardHandle(e));
        btn3.setOnMouseClicked(e -> bookHandle(e));

    }

    public void flightHandle(MouseEvent s) {

        btn1.setOpacity(0.5);
        btn2.setOpacity(1);
        btn3.setOpacity(0.5);
        rootLayout.getChildren().remove(mainLayout);
        rootLayout.getChildren().remove(flightLayout);
        rootLayout.getChildren().remove(bookLayout);
        try {
            flightLayout = FXMLLoader.load(getClass().getResource("/fxml/FXMLCustomerFlightView.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLCustomerMainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        rootLayout.getChildren().add(flightLayout);
    }
    
        public void DashBoardHandle(MouseEvent s) {
            
        btn1.setOpacity(1);
        btn2.setOpacity(0.5);
        btn3.setOpacity(0.5);

        rootLayout.getChildren().remove(mainLayout);
        rootLayout.getChildren().remove(flightLayout);
        rootLayout.getChildren().remove(bookLayout);

        try {
            mainLayout = FXMLLoader.load(getClass().getResource("/fxml/FXMLCustomerDashBoardView.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLCustomerMainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        rootLayout.getChildren().add(mainLayout);
    }

    
        public void bookHandle(MouseEvent s) {

        btn1.setOpacity(0.5);
        btn2.setOpacity(0.5);
        btn3.setOpacity(1);
        rootLayout.getChildren().remove(mainLayout);
        rootLayout.getChildren().remove(flightLayout);
        rootLayout.getChildren().remove(bookLayout);

        try {
            bookLayout = FXMLLoader.load(getClass().getResource("/fxml/FXMLCustomerBookView.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLCustomerMainController.class.getName()).log(Level.SEVERE, null, ex);
        }

        rootLayout.getChildren().add(bookLayout);
    }

}
