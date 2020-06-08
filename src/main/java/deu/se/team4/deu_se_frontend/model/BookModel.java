/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deu.se.team4.deu_se_frontend.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.json.JSONObject;

/**
 *
 * @author 강호동
 */
public class BookModel extends BaseModel {

    private static final String BOOK_CHECK_SEAT = "booking/check_seat";
    private static final String BOOK_SEAT_REMAIN = "booking/check_seat_remain";
    private static final String BOOK_FLIGHT = "booking/flight";

    @Override
    void createModel(String stringJson) {
    }

    @Override
    void printModel() {
    }

    Boolean bookFlight(String strJson) {

        String raw_string = super.postNonAPISynchronous(BOOK_FLIGHT, strJson);

        // list = gson.fromJson(raw_string, listType);   
        return true;

    }

    Boolean checkCanBook(String identifier, String seat_grade) {

        JSONObject jsvar = new JSONObject();
        jsvar.put("apiKey", APICenter.getInstance().getApiKey());
        jsvar.put("identifier", identifier);

        String raw_string = super.postNonAPISynchronous(BOOK_SEAT_REMAIN, jsvar.toString());
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(raw_string);

        if ("F".equals(seat_grade)) {
            return element.getAsJsonObject().get("first_seat_num").getAsInt() > 0;
        } else if ("B".equals(seat_grade)) {
            return element.getAsJsonObject().get("business_seat_num").getAsInt() > 0;

        } else if ("E".equals(seat_grade)) {
            return element.getAsJsonObject().get("economy_seat_num").getAsInt() > 0;

        }

        return false;

    }

    JsonElement countCanBook(String identifier) {
        JSONObject jsvar = new JSONObject();
        jsvar.put("apiKey", APICenter.getInstance().getApiKey());
        jsvar.put("identifier", identifier);

        String raw_string = super.postNonAPISynchronous(BOOK_SEAT_REMAIN, jsvar.toString());
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(raw_string);
        
        return element;

    }

    int setBookSeat(String identifier, String seat_grade) {
        JSONObject jsvar = new JSONObject();
        jsvar.put("apiKey", APICenter.getInstance().getApiKey());
        jsvar.put("identifier", identifier);

        String raw_string = super.postNonAPISynchronous(BOOK_CHECK_SEAT, jsvar.toString());
        //파싱해서 예약될 좌석 번호 리턴
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(raw_string);

        if ("F".equals(seat_grade)) {
            return element.getAsJsonObject().get("first_seat_num").getAsInt();
        } else if ("B".equals(seat_grade)) {
            return element.getAsJsonObject().get("business_seat_num").getAsInt();
        } else if ("E".equals(seat_grade)) {
            return element.getAsJsonObject().get("economy_seat_num").getAsInt();
        }

        return 0;
    }

}
