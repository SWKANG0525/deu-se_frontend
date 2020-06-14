/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deu.se.team4.deu_se_frontend;

import deu.se.team4.deu_se_frontend.model.APICenter;
import java.util.concurrent.SubmissionPublisher;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 강호동
 */
public class NotifyData extends SubmissionPublisher<NotifyData.Values> {

    private NotifyData.Values values = new NotifyData.Values(APICenter.getInstance().getBookAmountByAirlineKor()); // Not 0. getbookAmount Make API

    public void setMeasurements(int values) {
        
        if(this.values.getBookAmount() != values) {
        this.values.setBookAmount(values);

        this.submit(this.values);
        }
        try {
            
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(NotifyData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public class Values {

        private int book_amount;

        public Values(int book_amount) {
            this.book_amount = book_amount;
        }

        public int getBookAmount() {
            return this.book_amount;

        }

        public void setBookAmount(int book_amount) {
            this.book_amount = book_amount;
        }

    }
}
