package deu.se.team4.deu_se_frontend;

import java.util.ArrayList;
import java.util.Iterator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 강호동
 */
public class ParkArea extends ParkComponent {

    private Iterator <ParkComponent > iterator =null;
    private final ArrayList <ParkComponent > parkComponents =new ArrayList <>();
    private final String park_area;
    public ParkArea(String park_area) {
        this.park_area = park_area;
      
    }
    public void add(ParkComponent parkComponent) {
        parkComponents.add(parkComponent);
    }

    public void remove(ParkComponent parkComponent) {
        parkComponents.remove(parkComponent);
        }

    public ParkComponent getChild(int i) {
        return parkComponents.get(i);
    }

    public String getParkArea() {
        return park_area;

    }

    @Override
    public Iterator<ParkComponent> createIterator() {
        if (iterator == null) {
            iterator = new CompositeIterator(parkComponents.iterator());
        }
        return iterator;
    }
    
    @Override
    public void print() {
        System.out.print("\n"+ getParkArea());
        System.out.println("--------------------");
        
        Iterator<ParkComponent > it = parkComponents.iterator();
        while( it.hasNext()) {
            ParkComponent parkComponent = it.next();
            parkComponent.print();
        }
}

}
