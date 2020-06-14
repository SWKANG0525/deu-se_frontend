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
public class NullIterator implements Iterator <ParkComponent > {
    @Override
    public ParkComponent next() {
        return null;
    }
    @Override
    public boolean hasNext() {
        return false;
    }
}
