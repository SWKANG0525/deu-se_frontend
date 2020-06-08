/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deu.se.team4.deu_se_frontend.controller;

import deu.se.team4.deu_se_frontend.AnimationManage;
import deu.se.team4.deu_se_frontend.EnumAccount;
import deu.se.team4.deu_se_frontend.model.APICenter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 *
 * @author 강호동
 */
public class FXMLLoginController implements Initializable {

    @FXML
    TextField id;
    @FXML
    TextField pw;
    @FXML
    Line accountline;
    @FXML
    Label first;
    @FXML
    AnchorPane flow0;
    @FXML
    private Label customer;
    @FXML
    private Label airline_staff;

    private AnimationManage anim;
    private Timer anim_timer;
    private TimerTask anim_task;
    private TimerTask anim_task_flow0;
    private EnumAccount account_type;
    private String next_fxml;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        anim = new AnimationManage();
        anim_timer = new Timer();

        FirstEvent();
        customer.setOnMouseClicked(e -> customerEvent(e));
        airline_staff.setOnMouseClicked(e -> airlineEvent(e));
        id.setOnKeyPressed(e -> idEvent(e));
        pw.setOnKeyPressed(e -> pwEvent(e));

    }

    private void customerEvent(MouseEvent e) {

        flow0.setDisable(true);
        anim.fadein(flow0, 2);
        anim_task = new TimerTask() {
            @Override
            public void run() {
                account_type = EnumAccount.CUSTOMER;
                APICenter.getInstance().setAccountType(account_type);
                accountline.setVisible(true);
                id.setVisible(true);
                id.setDisable(false);
                accountline.setDisable(false);
                anim.fadeout(accountline, 1);
                anim.fadeout(id, 1);
            }
        };
        anim_timer.schedule(anim_task, 1500);

    }

    private void FirstEvent() {

        first.setVisible(true);
        anim.fadeout(first, 1);
        anim_task = new TimerTask() {
            @Override
            public void run() {
                anim.fadein(first, 1);
                //  anim.fadeout(id, 1.5);
            }
        };
        anim_timer.schedule(anim_task, 1000);

        anim_task_flow0 = new TimerTask() {
            @Override
            public void run() {
                first.setVisible(false);
                flow0.setVisible(true);
                flow0.setDisable(false);
            }
        };
        anim_timer.schedule(anim_task_flow0, 2000);

    }

    private void airlineEvent(MouseEvent e) {
        flow0.setDisable(true);
        anim.fadein(flow0, 2);
        anim_task = new TimerTask() {
            @Override
            public void run() {
                //setLoginStrategy(new AirlineStaffLoginStrategy());
                account_type = EnumAccount.AIRLINE_STAFF;
                APICenter.getInstance().setAccountType(account_type);
                accountline.setVisible(true);
                id.setVisible(true);
                id.setDisable(false);
                accountline.setDisable(false);
                anim.fadeout(accountline, 1);
                anim.fadeout(id, 1);
            }

        };
        anim_timer.schedule(anim_task, 1500);

    }

    private void idEvent(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER) {
            
            if (IDEventHandler(account_type)) {
                anim.fadein(id, 1);
                anim.fadein(accountline, 1);
                anim_task = new TimerTask() {
                    @Override
                    public void run() {
                        pw.setVisible(true);
                        pw.setDisable(false);
                        anim.fadeout(pw, 1);
                        anim.fadeout(accountline, 1);

                    }
                };
                anim_timer.schedule(anim_task, 1000);

            }
        }

    }

    private void pwEvent(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER) {
                    if(PWEventHandler(account_type)) {
                try {

                    Stage stage = (Stage) pw.getScene().getWindow();

                    Parent login = FXMLLoader.load(getClass().getResource(next_fxml));

                    Scene scene = new Scene(login);

                    stage.setScene(scene);

                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean IDEventHandler(EnumAccount account_type) {
        if (APICenter.getInstance().isIDExist(id.getText())) {
            id.setStyle("-fx-background-color: transparent; -fx-text-fill: red; -fx-font-size: 30px; -fx-font-family: NanumSquare Bold;");
            return false;
        } else {
            APICenter.getInstance().setID(id.getText());
            return true;
        }
    }

    private boolean PWEventHandler(EnumAccount account_type) {
        next_fxml = APICenter.getInstance().logInAccount(id.getText(), pw.getText());
        if (!"false".equals(next_fxml)) {
            pw.setDisable(true);
            anim.fadein(pw, 1.5);
            anim.fadein(accountline, 1.5);
            return true;
        } else {
            pw.setStyle("-fx-background-color: transparent; -fx-text-fill: red; -fx-font-size: 30px; -fx-font-family: NanumSquare Bold;");
            return false;
        }
    }

}
