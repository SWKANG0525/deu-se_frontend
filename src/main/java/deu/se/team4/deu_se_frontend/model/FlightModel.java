/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deu.se.team4.deu_se_frontend.model;

import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import deu.se.team4.deu_se_frontend.vo.FlightVO;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author 강호동
 */
public class FlightModel extends BaseModel {

    private static final String FLIGHT_SELECT_DATE_LIST = "flight/date";
    private static final String FLIGHT_SELECT_AIRLINE_KOR_LIST = "flight/list";
    private static final String ADD_FLIGHT = "flight/register";

    @Override
    void createModel(String stringJson) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void printModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    List<FlightVO> generateFlightByDate(String start_date) {

        JSONObject jsvar = new JSONObject();
        jsvar.put("apiKey", APICenter.getInstance().getApiKey());
        jsvar.put("start_date", start_date);
        String raw_string = super.postNonAPISynchronous(FLIGHT_SELECT_DATE_LIST, jsvar.toString());
        
        
        raw_string = raw_string.substring(10, raw_string.length() - 1);
        
        try {
        List<FlightVO> flight_list = gson.fromJson(raw_string, new TypeToken<List<FlightVO>>() {
        }.getType());
           return flight_list;
        // list = gson.fromJson(raw_string, listType);   
        } catch(JsonSyntaxException e) {
            List<FlightVO> flight_list = new ArrayList<>();
            return flight_list;
            
        }

    }
    
        List<FlightVO> generateFlightByAirlineKor(String airline_kor) {

        JSONObject jsvar = new JSONObject();
        jsvar.put("apiKey", APICenter.getInstance().getApiKey());
        jsvar.put("airline_kor", airline_kor);
        String raw_string = super.postNonAPISynchronous(FLIGHT_SELECT_AIRLINE_KOR_LIST, jsvar.toString());
        
        
        raw_string = raw_string.substring(10, raw_string.length() - 1);
        
        try {
        List<FlightVO> flight_list = gson.fromJson(raw_string, new TypeToken<List<FlightVO>>() {
        }.getType());
           return flight_list;
        // list = gson.fromJson(raw_string, listType);   
        } catch(JsonSyntaxException e) {
            List<FlightVO> flight_list = new ArrayList<>();
            return flight_list;
            
        }

    }

    Boolean addFlight(String strJson) {



        String raw_string = super.postNonAPISynchronous(ADD_FLIGHT, strJson);
       
        // list = gson.fromJson(raw_string, listType);   

        return true;

    }

}
