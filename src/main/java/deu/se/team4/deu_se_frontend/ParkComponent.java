/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deu.se.team4.deu_se_frontend;

import java.util.Iterator;

/**
 *
 * @author 강호동
 */
public abstract class ParkComponent {
    
     public void add(ParkComponent parkComponent) {
        throw new UnsupportedOperationException();
    }
    public void remove(ParkComponent parkComponent) {
        throw new UnsupportedOperationException();
    }
    public ParkComponent getChild(int i) {
        throw new UnsupportedOperationException();
    }
    public String getParkArea() {
        throw new UnsupportedOperationException();
    }
    public String getExp() {
        throw new UnsupportedOperationException();
    }
    public int getBookID() {
        throw new UnsupportedOperationException();
    }
    public int getParkPos() {
        throw new UnsupportedOperationException();
    }
    public String getCarNumber() {
        throw new UnsupportedOperationException();
    }

    public abstract Iterator <ParkComponent > createIterator();
    public void print() {
        throw new UnsupportedOperationException();
    }
}
   

