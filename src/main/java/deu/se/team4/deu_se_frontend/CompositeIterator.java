/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deu.se.team4.deu_se_frontend;

import java.util.Iterator;
import java.util.Stack;

/**
 *
 * @author 강호동
 */
public class CompositeIterator implements Iterator <ParkComponent > {
    
    private final Stack <Iterator <ParkComponent >> stack =new Stack <>();
    
    public CompositeIterator(Iterator <ParkComponent > iterator) {
        stack.push(iterator);
    }
    @Override
    public ParkComponent next() {
        if(hasNext()) {
            Iterator<ParkComponent > iterator = stack.peek();
            ParkComponent component = iterator.next();
            stack.push(component.createIterator());
            return component;
        } else {
            return null;
        }
    }
    @Override
    public boolean hasNext() {
        if (stack.empty()) {
            return false;
        } else {
            Iterator<ParkComponent > iterator = stack.peek();
            if (!iterator.hasNext()) {
                stack.pop();
                return hasNext();
            } else {
                return true;
            }
        }
    }
}