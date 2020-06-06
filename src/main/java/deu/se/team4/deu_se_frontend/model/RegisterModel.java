/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deu.se.team4.deu_se_frontend.model;

import deu.se.team4.deu_se_frontend.EnumAccount;
import deu.se.team4.deu_se_frontend.vo.RegisterVO;

/**
 *
 * @author 강호동
 */
class RegisterModel extends BaseModel {

    private String module_url;

    public RegisterModel() {

    }

    //createModel(account.toString());
    @Override
    void createModel(String stringJson) {
    }

    Boolean createAccount(String stringJson, EnumAccount account_type) {
        if (account_type == EnumAccount.AIRLINE_STAFF) {
            module_url = "register/airline_staff";
        } else {
            module_url = "register/customer";
        }
        RegisterVO register = gson.fromJson(super.postNonAPISynchronous(module_url, stringJson), RegisterVO.class);

        if ("true".equals(register.getResult())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void printModel() {
        // System.out.println()
    }
}
