/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deu.se.team4.deu_se_frontend.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *
 * @author 강호동
 */
abstract class BaseModel {

    private static final String baseUrl = APICenter.getInstance().getServerAddr();

    protected String result;
    protected Gson gson;
    private OkHttpClient client;
    private Request request;
    private String apiKey;

    protected BaseModel() {
        this.client = new OkHttpClient();
        gson = new GsonBuilder().create();

    }

    abstract void createModel(String stringJson);

    abstract void printModel();

    public String getSynchronous(String moduleURL) {
        try {
            request = new Request.Builder()
                    //.addHeader("x-api-key", this.apiKey)
                    .url(this.baseUrl + moduleURL)
                    .build(); //GET Request 

            //동기 처리시 execute함수 사용 
            Response response = client.newCall(request).execute();

            //출력 
            String message = response.body().string();
            System.out.println(message);
            return message;
        } catch (Exception e) {
            System.err.println(e.toString());
            return e.toString();

        }

    }

//  public String getASynchronous(String moduleURL) {
//      
//      
//
//		try {
//			Request request = new Request.Builder()
//					.addHeader("x-api-key", this.apiKey)
//					.url(this.baseUrl+moduleURL)
//					.build();
//
//			//비동기 처리 (enqueue 사용)
//			client.newCall(request).enqueue(new Callback() {
//				//비동기 처리를 위해 Callback 구현 
//				@Override
//				public void onFailure(Call call, IOException e) {
//					System.out.println("error + Connect Server Error is " + e.toString());
//				}
//
//				@Override
//				public void onResponse(Call call, Response response) throws IOException {
//					System.out.println("Response Body is " + response.body().string());
//                                        result = response.body().string();
//				}
//			});
//
//		} catch (Exception e){
//			System.err.println(e.toString());
//                        return e.toString();
//
//		}
//	}
    public String postSynchronous(String moduleURL, String jsonMessage) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .addHeader("x-api-key", this.apiKey)
                    .url(this.baseUrl + moduleURL)
                    .post(RequestBody.create(MediaType.parse("application/json"), jsonMessage)) //POST로 전달할 내용 설정 
                    .build();

            //동기 처리시 execute함수 사용
            Response response = client.newCall(request).execute();

            //출력
            String message = response.body().string();
            System.out.println(message);
            return message;

        } catch (Exception e) {
            System.err.println(e.toString());
            return e.toString();
        }
    }

    public String postNonAPISynchronous(String moduleURL, String jsonMessage) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(this.baseUrl + moduleURL)
                    .post(RequestBody.create(MediaType.parse("application/json"), jsonMessage)) //POST로 전달할 내용 설정 
                    .build();

            //동기 처리시 execute함수 사용
            Response response = client.newCall(request).execute();

            //출력
            String message = response.body().string();
            System.out.println(message);
            return message;

        } catch (Exception e) {
            System.err.println(e.toString());
            return e.toString();
        }
    }

//public void postASynchronous(String moduleURL, String message) {
//
//		try{
//			Request request = new Request.Builder()
//					.addHeader("x-api-key", this.apiKey)
//					.url(this.baseUrl+moduleURL)
//					.post(RequestBody.create(MediaType.parse("application/json"), message))
//					.build();
//
//			//비동기 처리 (enqueue 사용)
//			client.newCall(request).enqueue(new Callback() {
//				//비동기 처리를 위해 Callback 구현
//				@Override
//				public void onFailure(Call call, IOException e) {
//					System.out.println("error + Connect Server Error is " + e.toString());
//				}
//
//				@Override
//				public void onResponse(Call call, Response response) throws IOException {
//					System.out.println("Response Body is " + response.body().string());
//				}
//			});
//
//		} catch (Exception e) {
//			System.err.println(e.toString());
//		}
//	}
//
//    
}
