/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deu.se.team4.deu_se_frontend;

import deu.se.team4.deu_se_frontend.model.APICenter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Flow;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

/**
 *
 * @author 강호동
 */    
public class NotifyDisplay implements Flow.Subscriber<NotifyData.Values>, DisplayElement, Initializable{
    
    private int book_amount;
    private Flow.Subscription subscription = null;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription=subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(NotifyData.Values item) {
        this.book_amount = item.getBookAmount();
        display();
        this.subscription.request(1);

    }

    @Override
    public void onError(Throwable throwable) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onComplete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void display() {
                System.out.println("변경 감지");
                APICenter.getInstance().setBookObserveVar(true);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
}