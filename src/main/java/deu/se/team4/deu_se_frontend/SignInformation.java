/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deu.se.team4.deu_se_frontend;

import org.json.JSONObject;

/**
 *
 * @author 강호동
 */
public class SignInformation {

    private final String name_eng;
    private final String name_kor;
    private final String id;
    private final String pw;
    private final String birth;
    private final String gender;
    private final String tel;
    private final EnumAccount account_type;

    private String airline_kor;
    private String position;
    private String passport_issue;
    private String passport_exp;
    private String passport_num;
    private String address;

    public SignInformation(AirlineStaffBuilder builder) {
        this.name_eng = builder.name_eng;
        this.name_kor = builder.name_kor;
        this.id = builder.id;
        this.pw = builder.pw;
        this.birth = builder.birth;
        this.gender = builder.gender;
        this.tel = builder.tel;
        this.account_type = builder.account_type;
        this.airline_kor = builder.airline_kor;
        this.position = builder.position;

    }

    public SignInformation(CustomerBuilder builder) {
        this.name_eng = builder.name_eng;
        this.name_kor = builder.name_kor;
        this.id = builder.id;
        this.pw = builder.pw;
        this.birth = builder.birth;
        this.gender = builder.gender;
        this.tel = builder.tel;
        this.account_type = builder.account_type;
        this.passport_issue = builder.passport_issue;
        this.passport_exp = builder.passport_exp;
        this.passport_num = builder.passport_num;
        this.address = builder.address;

    }

    public String toJsonStringCustomer() {

        JSONObject jsvar = new JSONObject();
        jsvar.put("name_eng", this.name_eng);
        jsvar.put("name_kor", this.name_kor);
        jsvar.put("id", this.id);
        jsvar.put("pw", this.pw);
        jsvar.put("birth", this.birth);
        jsvar.put("gender", this.gender);
        jsvar.put("tel", this.tel);
        jsvar.put("account_type", this.account_type);
        jsvar.put("passport_issue", this.passport_issue);
        jsvar.put("passport_exp", this.passport_exp);
        jsvar.put("passport_num", this.passport_num);
        jsvar.put("address", this.address);
        return jsvar.toString();
    }

    public String toJsonStringAirlineStaff() {

        JSONObject jsvar = new JSONObject();
        jsvar.put("name_eng", this.name_eng);
        jsvar.put("name_kor", this.name_kor);
        jsvar.put("id", this.id);
        jsvar.put("pw", this.pw);
        jsvar.put("birth", this.birth);
        jsvar.put("gender", this.gender);
        jsvar.put("tel", this.tel);
        jsvar.put("account_type", this.account_type);
        jsvar.put("airline_kor", this.airline_kor);
        jsvar.put("position", this.position);
        return jsvar.toString();

    }

    public static class AirlineStaffBuilder extends UserBuilder {

        private String airline_kor;
        private String position;

        public AirlineStaffBuilder setAirline_kor(String airline_kor) {
            this.airline_kor = airline_kor;
            return this;
        }

        public AirlineStaffBuilder setPosition(String position) {
            this.position = position;
            return this;
        }

        @Override
        public SignInformation build() {
            return new SignInformation(this);
        }
    }

    public static class CustomerBuilder extends UserBuilder {

        private String passport_issue;
        private String passport_exp;
        private String passport_num;
        private String address;

        public CustomerBuilder setPassport_issue(String passport_issue) {
            this.passport_issue = passport_issue;
            return this;
        }

        public CustomerBuilder setPassport_exp(String passport_exp) {
            this.passport_exp = passport_exp;
            return this;
        }

        public CustomerBuilder setPassport_num(String passport_num) {
            this.passport_num = passport_num;
            return this;
        }

        public CustomerBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        @Override
        public SignInformation build() {
            return new SignInformation(this);
        }
    }

}
