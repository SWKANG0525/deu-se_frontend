/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deu.se.team4.deu_se_frontend.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import deu.se.team4.deu_se_frontend.EnumAccount;
import deu.se.team4.deu_se_frontend.LoginStrategy;
import deu.se.team4.deu_se_frontend.vo.LoginVO;
import org.json.JSONObject;

/**
 *
 * @author 강호동
 */
final class LoginModel extends BaseModel {

    private static final String LOGIN_PROCESS_MODULE_URL = "auth/login";
    private static final String GET_AIRLINE_KOR_URL = "auth/api_verify";
    private static final String FXML_CUSTOMER_MAIN = "/fxml/FXMLCustomerMainView.fxml";
    private static final String FXML_AIRLINE_STAFF_MAIN = "/fxml/FXMLAirlineStaffMainView.fxml";
    private static final String FXML_AIRPORT_STAFF_MAIN = "/fxml/FXMLAirPortStaffMainView.fxml";
    private static final String GET_AIRLINE_STAFF_NAME = "airline_staff/name";
    private static final String GET_CUSTOMER_NAME = "customers/name";

    private LoginVO login;

    private LoginStrategy loginStrategy;

    public void setLoginStrategy(EnumAccount account_type) {

        switch (account_type) {
            case CUSTOMER:
                this.loginStrategy = new CustomerLoginStrategy();
                return;
            case AIRLINE_STAFF:
                this.loginStrategy = new AirlineStaffLoginStrategy();
                return;
            case AIRPORT_STAFF:
                this.loginStrategy = new AirportStaffLoginStrategy();

        }
    }

    public LoginModel() {

    }

    @Override
    void createModel(String stringJson) {

    }

    @Override
    public void printModel() {
        System.out.println("token : " + login.getToken());
    }

    public Boolean checkId(String id, EnumAccount account_type) {
        return loginStrategy.checkId(account_type.getName() + "s/" + id);
    }

    public String login(String id, String pw, EnumAccount account_type) {
        JSONObject jsvar = new JSONObject();
        
        jsvar.put("id", id);
        jsvar.put("pw", pw);
        jsvar.put("account_type", account_type.getName());
        return loginStrategy.loginProcess(jsvar.toString());

    }

    public String getCustomerName(String id) {
        JSONObject jsvar = new JSONObject();
        jsvar.put("id", id);
        String rawData = postNonAPISynchronous(GET_CUSTOMER_NAME, jsvar.toString());
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(rawData);
        return element.getAsJsonObject().get("result").getAsString();

    }

    public String getAirlineStaffName(String id) {
        JSONObject jsvar = new JSONObject();
        jsvar.put("id",id );
        String rawData = postNonAPISynchronous(GET_AIRLINE_STAFF_NAME, jsvar.toString());
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(rawData);
        return element.getAsJsonObject().get("result").getAsString();

    }

    public String getAirline_kor() {
        JSONObject jsvar = new JSONObject();
        jsvar.put("apiKey", APICenter.getInstance().getApiKey());
        String rawData = postNonAPISynchronous(GET_AIRLINE_KOR_URL, jsvar.toString());
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(rawData);
        return element.getAsJsonObject().get("airline_kor").getAsString();

    }

    private class AirlineStaffLoginStrategy implements LoginStrategy {

        @Override
        public String loginProcess(String jsonString) {

            login = gson.fromJson(postNonAPISynchronous(LOGIN_PROCESS_MODULE_URL, jsonString), LoginVO.class);

            if ("false".equals(login.getToken())) {
                return "false";
            } else {
                APICenter.getInstance().setApiKey(login.getToken());
                return FXML_AIRLINE_STAFF_MAIN;
            }

        }

        @Override
        public Boolean checkId(String jsonString) {
            String rawData = getSynchronous(jsonString);
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(rawData);
            return element.getAsJsonObject().get("result").getAsBoolean();
        }

    }

    private class AirportStaffLoginStrategy implements LoginStrategy {

        @Override
        public String loginProcess(String jsonString) {

            login = gson.fromJson(postNonAPISynchronous(LOGIN_PROCESS_MODULE_URL, jsonString), LoginVO.class);

            if ("false".equals(login.getToken())) {
                return "false";
            } else {
                APICenter.getInstance().setApiKey(login.getToken());
                return FXML_AIRPORT_STAFF_MAIN;
            }

        }

        @Override
        public Boolean checkId(String jsonString) {
            String rawData = getSynchronous(jsonString);
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(rawData);
            return element.getAsJsonObject().get("result").getAsBoolean();
        }

    }

    private class CustomerLoginStrategy implements LoginStrategy {

        @Override
        public String loginProcess(String jsonString) {

            login = gson.fromJson(postNonAPISynchronous(LOGIN_PROCESS_MODULE_URL, jsonString), LoginVO.class);

            if ("false".equals(login.getToken())) {
                return "false";
            } else {
                APICenter.getInstance().setApiKey(login.getToken());
                return FXML_CUSTOMER_MAIN;

            }
        }

        @Override
        public Boolean checkId(String jsonString) {

            String rawData = getSynchronous(jsonString);
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(rawData);
            return element.getAsJsonObject().get("result").getAsBoolean();
        }

    }
}
