/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deu.se.team4.deu_se_frontend;

/**
 *
 * @author 강호동
 */
public enum EnumAccount {
    CUSTOMER("customer"), AIRLINE_STAFF("airline_staff"), AIRPORT_STAFF("airport_staff");

    final private String name;

    public String getName() {
        return name;
    }

    private EnumAccount(String name) {
        this.name = name;
    }

}
