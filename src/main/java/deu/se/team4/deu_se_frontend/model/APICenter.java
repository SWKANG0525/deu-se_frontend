/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deu.se.team4.deu_se_frontend.model;

import com.google.gson.JsonElement;
import deu.se.team4.deu_se_frontend.EnumAccount;
import deu.se.team4.deu_se_frontend.vo.BookVO;
import deu.se.team4.deu_se_frontend.vo.FlightVO;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author 강호동
 */
//Facade, Signleton
public class APICenter {

    private Boolean book_observe_var = false;
    private String apiKey;
    private String id;
    private EnumAccount account_type;
    private static final APICenter API_CENTER = new APICenter();

    private LoginModel login_model;
    private RegisterModel register_model;
    private VersionModel version_model;
    private FlightModel flight_model;
    private BookModel book_model;
    private String SERVER_ADDR = "http://localhost:3000/";
    private String build_num;

    private APICenter() {

    }

    public Boolean getBookObserveVar() {
        return this.book_observe_var;
    }

    public void setBookObserveVar(Boolean bool) {
        this.book_observe_var = bool;
    }

    public String getServerAddr() {
        return SERVER_ADDR;
    }

    public final String getID() {
        return this.id;
    }

    public final int getBookAmountByAirlineKor() {
        book_model = new BookModel();
        return book_model.getBookAmountByAirlineKor();
    }

    public static APICenter getInstance() {
        return API_CENTER;
    }

    public final void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public final String getApiKey() {
        return this.apiKey;
    }

    public final void setID(String id) {
        this.id = id;
    }

    public final void setAccountType(EnumAccount account_type) {

        this.account_type = account_type;
    }

    public final EnumAccount getAccountType() {
        return this.account_type;
    }

    public Boolean isIDExist(String id) {
        if (account_type == null) {
            return false;
        }
        login_model = new LoginModel();

        login_model.setLoginStrategy(account_type);

        return login_model.checkId(id, account_type);

    }

    public Boolean registerAccount(String strJson) {
        if (account_type == null) {
            return false;
        }
        register_model = new RegisterModel();

        return register_model.createAccount(strJson, account_type);
    }

    public String logInAccount(String id, String pw) {
        if (account_type == null) {
            return "false";
        }
        login_model = new LoginModel();

        login_model.setLoginStrategy(account_type);
        return login_model.login(id, pw, account_type);

    }

    public String isVersionVaild() {

        version_model = new VersionModel();

        return version_model.VersionCheck();
    }

    public List<FlightVO> getFlightByDate(String start_date) {

        flight_model = new FlightModel();
        return flight_model.generateFlightByDate(start_date);

    }

    public String getAirlineKor() {
        if (apiKey == null) {
            return "false";
        }
        login_model = new LoginModel();

        return login_model.getAirline_kor();

    }

    public List<FlightVO> getFlightByAirlineKor(String airline_kor) {

        flight_model = new FlightModel();
        return flight_model.generateFlightByAirlineKor(airline_kor);

    }

    public List<BookVO> getBookByCustomerId() {

        book_model = new BookModel();
        return book_model.generateBookByCustomerId(id);

    }

    public FlightVO getFlightByIdentifier(String identifier) {

        flight_model = new FlightModel();

        return flight_model.generateFlightByIdentifier(identifier);
    }

    public Boolean addFlight(FlightVO flight_vo) {
        JSONObject jsvar = new JSONObject();
        jsvar.put("apiKey", APICenter.getInstance().getApiKey());
        jsvar.put("airline_kor", APICenter.getInstance().getAirlineKor());
        jsvar.put("airplane_name", flight_vo.airplane_name);
        jsvar.put("start_airport", flight_vo.start_airport);
        jsvar.put("dest_airport", flight_vo.dest_airport);
        jsvar.put("via", flight_vo.via);
        jsvar.put("identifier", flight_vo.identifier);
        jsvar.put("status", "schedule");
        jsvar.put("start_date", flight_vo.start_date);
        jsvar.put("end_date", flight_vo.end_date);
        jsvar.put("first_seat_num", flight_vo.first_seat_num);
        jsvar.put("business_seat_num", flight_vo.business_seat_num);
        jsvar.put("economy_seat_num", flight_vo.economy_seat_num);
        jsvar.put("sign", flight_vo.sign);

        flight_model = new FlightModel();

        return flight_model.addFlight(jsvar.toString());

    }

    public Boolean bookFlight(BookVO book_vo) {

        book_model = new BookModel();
        if (book_model.checkCanBook(book_vo.identifier, book_vo.seat_grade)) {
            System.out.println(book_vo.identifier);
            System.out.println(book_vo.seat_grade);

            JSONObject jsvar = new JSONObject();
            jsvar.put("apiKey", APICenter.getInstance().getApiKey());

            jsvar.put("identifier", book_vo.identifier);
            jsvar.put("seat_grade", book_vo.seat_grade);
            jsvar.put("seat_num", book_model.setBookSeat(book_vo.identifier, book_vo.seat_grade));
            jsvar.put("customer_id", id);
            jsvar.put("baggage_weight", book_vo.baggage_weight);
            jsvar.put("car_number", book_vo.car_number);
            return book_model.bookFlight(jsvar.toString());

        }
        return false;
    }

    public JsonElement countCanBook(String identifier) {

        book_model = new BookModel();
        return book_model.countCanBook(identifier);
    }

    public List<BookVO> getBookByIdentifier(String identifier) {

        book_model = new BookModel();
        return book_model.generateBookByIdentifier(identifier);

    }

    public boolean requestBookDelete(int book_id) {

        book_model = new BookModel();
        return book_model.deleteBook(book_id);
    }

    public String getCustomerName() {
        login_model = new LoginModel();

        return login_model.getCustomerName(id);

    }

    public String getAirlineStaffName() {
        login_model = new LoginModel();

        return login_model.getAirlineStaffName(id);

    }
}
