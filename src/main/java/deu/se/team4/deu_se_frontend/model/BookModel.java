/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deu.se.team4.deu_se_frontend.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import deu.se.team4.deu_se_frontend.vo.BookVO;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author 강호동
 */
public class BookModel extends BaseModel {

    private static final String BOOK_CHECK_SEAT = "booking/check_seat";
    private static final String BOOK_SEAT_REMAIN = "booking/check_seat_remain";
    private static final String BOOK_FLIGHT = "booking/flight";
    private static final String BOOK_LIST_BY_IDENTIFIER = "booking/list";
    private static final String BOOK_LIST_BY_CUSTOMER_ID = "booking/customer_id";
    private static final String BOOK_DELETE = "booking/delete";

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

    List<BookVO> generateBookByIdentifier(String identifier) {

        JSONObject jsvar = new JSONObject();
        jsvar.put("apiKey", APICenter.getInstance().getApiKey());
        jsvar.put("identifier", identifier);
        String raw_string = super.postNonAPISynchronous(BOOK_LIST_BY_IDENTIFIER, jsvar.toString());

        raw_string = raw_string.substring(10, raw_string.length() - 1);

        try {
            List<BookVO> book_list = gson.fromJson(raw_string, new TypeToken<List<BookVO>>() {
            }.getType());
            return book_list;
            // list = gson.fromJson(raw_string, listType);   
        } catch (JsonSyntaxException e) {
            List<BookVO> book_list = new ArrayList<>();
            return book_list;

        }

    }

    List<BookVO> generateBookByCustomerId(String customer_id) {

        JSONObject jsvar = new JSONObject();
        jsvar.put("apiKey", APICenter.getInstance().getApiKey());
        jsvar.put("customer_id", customer_id);
        String raw_string = super.postNonAPISynchronous(BOOK_LIST_BY_CUSTOMER_ID, jsvar.toString());

        raw_string = raw_string.substring(10, raw_string.length() - 1);

        try {
            List<BookVO> book_list = gson.fromJson(raw_string, new TypeToken<List<BookVO>>() {
            }.getType());
            return book_list;
            // list = gson.fromJson(raw_string, listType);   
        } catch (JsonSyntaxException e) {
            List<BookVO> book_list = new ArrayList<>();
            return book_list;

        }

    }

    Boolean deleteBook(int id) {

        JSONObject jsvar = new JSONObject();
        jsvar.put("apiKey", APICenter.getInstance().getApiKey());
        jsvar.put("id", id);

        String raw_string = super.postNonAPISynchronous(BOOK_DELETE, jsvar.toString());
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(raw_string);
        
        if (element.getAsJsonObject().get("result").getAsBoolean()) 
            return true;
        

        return false;

    }

}
