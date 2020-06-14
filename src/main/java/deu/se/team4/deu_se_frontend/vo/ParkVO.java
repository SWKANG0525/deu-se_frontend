/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deu.se.team4.deu_se_frontend.vo;

import deu.se.team4.deu_se_frontend.NullIterator;
import deu.se.team4.deu_se_frontend.NullIterator;
import deu.se.team4.deu_se_frontend.ParkComponent;
import deu.se.team4.deu_se_frontend.ParkComponent;
import java.util.Iterator;

/**
 *
 * @author 강호동
 */

public class ParkVO extends ParkComponent {
    private final int park_pos;
    private String car_number;
    private int book_id;
    private String exp;
    private String park_area;
    public ParkVO(int park_pos, String car_number, int book_id, String exp,String park_area) {
        this.park_pos = park_pos;
        this.car_number = car_number;
        this.book_id = book_id;
        this.exp = exp;
        this.park_area = park_area;
    }

    @Override
    public String getExp() {
        return this.exp;
    }

    
    @Override
    public int getBookID() {
        return this.book_id;
    }
    @Override
    public int getParkPos() {
        return this.park_pos;
    }
    
    @Override
    public String getParkArea() {
        return this.park_area;
    }
    
    @Override
    public String getCarNumber() {
        return this.car_number;
    }
    
    @Override
    public Iterator <ParkComponent > createIterator() {
        return new NullIterator();
    }
    
    @Override
    public void print() {
        System.out.print("Park Pos : "+ getParkPos());
        System.out.println("Car Number : "+getCarNumber());
        }
}