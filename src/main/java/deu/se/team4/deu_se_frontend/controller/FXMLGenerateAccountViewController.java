/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deu.se.team4.deu_se_frontend.controller;

import deu.se.team4.deu_se_frontend.model.APICenter;
import deu.se.team4.deu_se_frontend.AnimationManage;
import deu.se.team4.deu_se_frontend.EnumAccount;
import deu.se.team4.deu_se_frontend.SignInformation;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 강호동
 */
public class FXMLGenerateAccountViewController implements Initializable {

    @FXML
    private Label flow1;
    @FXML
    private Label flow2;
    @FXML
    private TextField flow3;
    @FXML
    private TextField flow4;
    @FXML
    private TextField flow5;
    @FXML
    private TextField flow6;
    @FXML
    private TextField flow7;
    @FXML
    private TextField flow8;
    @FXML
    private TextField flow9;
    @FXML
    private Label flow00;
    @FXML
    private ImageView flow00img;
    @FXML
    private Line accountline;
    @FXML
    private AnchorPane flow0;
    @FXML
    private AnchorPane flow10;
    @FXML
    private TextField flow11;
    @FXML
    private TextField flow12;
    @FXML
    private TextField flow13;
    @FXML
    private TextField flow14;
    @FXML
    private TextField flow20;
    @FXML
    private TextField flow21;
    @FXML
    private Label man;
    @FXML
    private Label woman;
    @FXML
    private Label customer;
    @FXML
    private Label airline_staff;

    private EnumAccount account_type;
    private String gender;
    private final AnimationManage anim = new AnimationManage();
    private final Timer anim_timer = new Timer();
    private TimerTask anim_task;
    private TimerTask anim_task_flow0;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        FlowWelcome();
        customer.setOnMouseClicked(e -> customerEvent(e));
        airline_staff.setOnMouseClicked(e -> airline_staffEvent(e));
        flow3.setOnKeyPressed(e -> name_engEvent(e));
        flow4.setOnKeyPressed(e -> name_korEvent(e));
        flow5.setOnKeyPressed(e -> birthEvent(e));
        flow6.setOnKeyPressed(e -> telEvent(e));
        flow7.setOnKeyPressed(e -> idEvent(e));
        flow8.setOnKeyPressed(e -> pwEvent(e));
        flow9.setOnKeyPressed(e -> rePwEvent(e));
        man.setOnMouseClicked(e -> manEvent(e));
        woman.setOnMouseClicked(e -> womanEvent(e));
        flow11.setOnKeyPressed(e -> passportIssueEvent(e));
        flow12.setOnKeyPressed(e -> passportExpEvent(e));
        flow13.setOnKeyPressed(e -> passportNumEvent(e));
        flow14.setOnKeyPressed(e -> addrAndCustomerRegisterEvent(e));
        flow20.setOnKeyPressed(e -> airlineKorEvent(e));
        flow21.setOnKeyPressed(e -> positionAndAirlineStaffRegisterEvent(e));
        flow00.setOnMouseClicked(e -> finishEvent(e));

    }

    private void FlowWelcome() {
        flow1.setVisible(true);
        anim.fadein(flow1, 1.5);

        anim_task = new TimerTask() {
            @Override
            public void run() {
                anim.fadeout(flow1, 1.5);
                flow1.setVisible(false);
                flow2.setVisible(true);
                anim.fadeout(flow2, 1.5);
                anim.fadein(flow2, 1.5);
            }

        };

        anim_timer.schedule(anim_task, 3000);

        anim_task_flow0 = new TimerTask() {
            @Override
            public void run() {
                flow2.setVisible(false);
                anim.fadeout(flow0, 2);
                flow0.setVisible(true);
                flow0.setDisable(false);
            }

        };

        anim_timer.schedule(anim_task_flow0, 5000);

    }

    private void customerEvent(MouseEvent event) {
        account_type = EnumAccount.CUSTOMER;
        APICenter.getInstance().setAccountType(account_type);
        flow0.setDisable(true);
        anim.fadein(flow0, 2);
        anim_task = new TimerTask() {
            @Override
            public void run() {

                flow3.setVisible(true);
                flow3.setDisable(false);
                anim.fadeout(flow3, 1.5);
                anim.fadeout(accountline, 1.5);
                accountline.setVisible(true);
            }

        };
        anim_timer.schedule(anim_task, 1500);

    }

    private void airline_staffEvent(MouseEvent event) {
        account_type = EnumAccount.AIRLINE_STAFF;
        APICenter.getInstance().setAccountType(account_type);

        flow0.setDisable(true);
        anim.fadein(flow0, 2);
        anim_task = new TimerTask() {
            @Override
            public void run() {
                flow3.setVisible(true);
                flow3.setDisable(false);
                anim.fadeout(flow3, 1.5);
                anim.fadeout(accountline, 1.5);
                accountline.setVisible(true);
            }

        };
        anim_timer.schedule(anim_task, 1500);
    }

    private void name_engEvent(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            flow3.setDisable(true);
            anim.fadein(flow3, 1.5);
            anim.fadein(accountline, 1.5);
            anim_task = new TimerTask() {
                @Override
                public void run() {
                    flow4.setVisible(true);
                    flow4.setDisable(false);
                    anim.fadeout(flow4, 1.5);
                    anim.fadeout(accountline, 1.5);

                }

            };
            anim_timer.schedule(anim_task, 1500);
        }
    }

    private void name_korEvent(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            flow4.setDisable(true);
            anim.fadein(flow4, 1.5);
            anim.fadein(accountline, 1.5);
            anim_task = new TimerTask() {
                @Override
                public void run() {
                    flow5.setVisible(true);
                    flow5.setDisable(false);
                    anim.fadeout(flow5, 1.5);
                    anim.fadeout(accountline, 1.5);

                }

            };
            anim_timer.schedule(anim_task, 1500);
        }
    }

    private void birthEvent(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER) {
            flow5.setDisable(true);
            anim.fadein(flow5, 1.5);
            anim.fadein(accountline, 1.5);
            anim_task = new TimerTask() {
                @Override
                public void run() {
                    flow6.setVisible(true);
                    flow6.setDisable(false);
                    anim.fadeout(flow6, 1.5);
                    anim.fadeout(accountline, 1.5);
                }
            };
            anim_timer.schedule(anim_task, 1500);
        }
    }

    private void telEvent(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER) {
            flow6.setDisable(true);
            anim.fadein(flow6, 1.5);
            anim.fadein(accountline, 1.5);
            anim_task = new TimerTask() {
                @Override
                public void run() {
                    flow7.setVisible(true);
                    flow7.setDisable(false);
                    anim.fadeout(flow7, 1.5);
                    anim.fadeout(accountline, 1.5);

                }

            };
            anim_timer.schedule(anim_task, 1500);
        }
    }

    private void idEvent(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER) {
            if (APICenter.getInstance().isIDExist(flow7.getText())) {
                APICenter.getInstance().setID(flow7.getText());
                flow7.setDisable(true);
                anim.fadein(flow7, 1.5);
                anim.fadein(accountline, 1.5);
                anim_task = new TimerTask() {
                    @Override
                    public void run() {
                        flow8.setVisible(true);
                        flow8.setDisable(false);
                        anim.fadeout(flow8, 1.5);
                        anim.fadeout(accountline, 1.5);
                    }
                };
                anim_timer.schedule(anim_task, 1500);

            } else {
                flow7.setStyle("-fx-background-color: transparent; -fx-text-fill: red; -fx-font-size: 30px; -fx-font-family: NanumSquare Bold;");
            }
        }
    }

    private void pwEvent(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER) {
            flow8.setDisable(true);
            anim.fadein(flow8, 1.5);
            anim.fadein(accountline, 1.5);
            anim_task = new TimerTask() {
                @Override
                public void run() {
                    flow9.setVisible(true);
                    flow9.setDisable(false);
                    anim.fadeout(flow9, 1.5);
                    anim.fadeout(accountline, 1.5);

                }

            };
            anim_timer.schedule(anim_task, 1500);
        }
    }

    private void rePwEvent(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            if (flow9.getText().equals(flow8.getText())) {
                flow9.setDisable(true);
                anim.fadein(flow9, 1.5);
                anim.fadein(accountline, 1.5);
                anim_task = new TimerTask() {
                    @Override
                    public void run() {
                        flow10.setVisible(true);
                        anim.fadeout(flow10, 1.5);
                        flow10.setDisable(false);
                    }

                };
                anim_timer.schedule(anim_task, 2000);
            } else {
                flow9.setStyle("-fx-background-color: transparent; -fx-text-fill: red; -fx-font-size: 30px; -fx-font-family: NanumSquare Bold;");
            }
        }
    }

    private void manEvent(MouseEvent event) {
        gender = "M";
        flow10.setDisable(true);
        anim.fadein(flow10, 2);
        anim_task = new TimerTask() {
            @Override
            public void run() {
                if (EnumAccount.CUSTOMER == account_type) {
                    flow11.setVisible(true);
                    flow11.setDisable(false);
                    anim.fadeout(flow11, 1.5);
                    anim.fadeout(accountline, 1.5);
                    accountline.setVisible(true);
                } else {
                    flow20.setVisible(true);
                    flow20.setDisable(false);
                    anim.fadeout(flow20, 1.5);
                    anim.fadeout(accountline, 1.5);
                    accountline.setVisible(true);

                }

            }

        };
        anim_timer.schedule(anim_task, 1500);
    }

    private void womanEvent(MouseEvent event) {

        gender = "W";
        flow10.setDisable(true);
        anim.fadein(flow10, 2);
        anim_task = new TimerTask() {
            @Override
            public void run() {
                if (EnumAccount.CUSTOMER == account_type) {
                    flow11.setVisible(true);
                    flow11.setDisable(false);
                    anim.fadeout(flow11, 1.5);
                    anim.fadeout(accountline, 1.5);
                    accountline.setVisible(true);
                } else {
                    flow20.setVisible(true);
                    flow20.setDisable(false);
                    anim.fadeout(flow20, 1.5);
                    anim.fadeout(accountline, 1.5);
                    accountline.setVisible(true);

                }
            }
        };
        anim_timer.schedule(anim_task, 1500);
    }

    private void passportIssueEvent(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            flow11.setDisable(true);
            anim.fadein(flow11, 1.5);
            anim.fadein(accountline, 1.5);
            anim_task = new TimerTask() {
                @Override
                public void run() {
                    flow12.setVisible(true);
                    flow12.setDisable(false);
                    anim.fadeout(flow12, 1.5);
                    anim.fadeout(accountline, 1.5);

                }

            };
            anim_timer.schedule(anim_task, 1500);
        }
    }

    private void passportExpEvent(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            flow12.setDisable(true);
            anim.fadein(flow12, 1.5);
            anim.fadein(accountline, 1.5);
            anim_task = new TimerTask() {
                @Override
                public void run() {
                    flow13.setVisible(true);
                    flow13.setDisable(false);
                    anim.fadeout(flow13, 1.5);
                    anim.fadeout(accountline, 1.5);

                }

            };
            anim_timer.schedule(anim_task, 1500);
        }
    }

    private void passportNumEvent(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            flow13.setDisable(true);
            anim.fadein(flow13, 1.5);
            anim.fadein(accountline, 1.5);
            anim_task = new TimerTask() {
                @Override
                public void run() {
                    flow14.setVisible(true);
                    flow14.setDisable(false);
                    anim.fadeout(flow14, 1.5);
                    anim.fadeout(accountline, 1.5);

                }

            };
            anim_timer.schedule(anim_task, 1500);
        }
    }

    private void addrAndCustomerRegisterEvent(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {

            APICenter.getInstance().registerAccount(new SignInformation.CustomerBuilder()
                    .setPassport_exp(flow12.getText())
                    .setPassport_issue(flow11.getText())
                    .setPassport_num(flow13.getText())
                    .setAddress(flow14.getText())
                    .setAccount_type(EnumAccount.CUSTOMER)
                    .setName_eng(flow3.getText())
                    .setName_kor(flow4.getText())
                    .setId(flow7.getText())
                    .setPw(flow9.getText())
                    .setBirth(flow5.getText())
                    .setGender(gender)
                    .setTel(flow6.getText())
                    .build()
                    .toJsonStringCustomer());

            flow14.setDisable(true);
            anim.fadein(flow14, 1.5);
            anim.fadein(accountline, 1.5);
            anim_task = new TimerTask() {
                @Override
                public void run() {
                    flow00.setVisible(true);
                    flow00.setDisable(false);
                    anim.fadeout(flow00, 1.5);
                    flow00img.setVisible(true);
                    anim.fadeout(flow00img, 1.5);
                }

            };
            anim_timer.schedule(anim_task, 1500);
        }

    }

    private void airlineKorEvent(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            flow20.setDisable(true);
            anim.fadein(flow20, 1.5);
            anim.fadein(accountline, 1.5);
            anim_task = new TimerTask() {
                @Override
                public void run() {
                    flow21.setVisible(true);
                    flow21.setDisable(false);
                    anim.fadeout(flow21, 1.5);
                    anim.fadeout(accountline, 1.5);

                }

            };
            anim_timer.schedule(anim_task, 1500);
        }
    }

    private void positionAndAirlineStaffRegisterEvent(KeyEvent event) {

        if (event.getCode() == KeyCode.ENTER) {
            APICenter.getInstance().registerAccount(new SignInformation.AirlineStaffBuilder()
                    .setAirline_kor(flow20.getText())
                    .setPosition(flow21.getText())
                    .setAccount_type(EnumAccount.AIRLINE_STAFF)
                    .setName_eng(flow3.getText())
                    .setName_kor(flow4.getText())
                    .setId(flow7.getText())
                    .setPw(flow9.getText())
                    .setBirth(flow5.getText())
                    .setGender(gender)
                    .setTel(flow6.getText())
                    .build()
                    .toJsonStringAirlineStaff());
            flow21.setDisable(true);
            anim.fadein(flow21, 1.5);
            anim.fadein(accountline, 1.5);
            anim_task = new TimerTask() {
                @Override
                public void run() {
                    flow00.setVisible(true);
                    flow00.setDisable(false);
                    anim.fadeout(flow00, 1.5);
                    flow00img.setVisible(true);
                    anim.fadeout(flow00img, 1.5);

                }

            };
            anim_timer.schedule(anim_task, 1500);
        }

    }

    private void finishEvent(MouseEvent event) {
        try {

            Stage stage = (Stage) flow10.getScene().getWindow();
            Parent login = null;

            
            switch(APICenter.getInstance().getAccountType()) {
                case CUSTOMER:
                    login = FXMLLoader.load(getClass().getResource("/fxml/FXMLCustomerMainView.fxml"));
                    break;
                case AIRLINE_STAFF:
                    login = FXMLLoader.load(getClass().getResource("/fxml/FXMLAirlineStaffMainView.fxml"));
                    break;
                    
                    
            }

            Scene scene = new Scene(login);

            stage.setScene(scene);

            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
