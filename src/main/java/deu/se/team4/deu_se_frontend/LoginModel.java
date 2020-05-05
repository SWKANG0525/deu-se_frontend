/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deu.se.team4.deu_se_frontend;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

/**
 *
 * @author 강호동
 */
public class LoginModel {
    
   public void post(String strUrl, JSONObject json){
		try {
			URL url = new URL(strUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setConnectTimeout(5000); //서버에 연결되는 Timeout 시간 설정
			con.setReadTimeout(5000); // InputStream 읽어 오는 Timeout 시간 설정
			//con.addRequestProperty("x-api-key", RestTestCommon.API_KEY); //key값 설정

			con.setRequestMethod("POST");
                                     //json으로 message를 전달하고자 할 때 
			con.setRequestProperty("Content-Type", "application/json");
			con.setDoInput(true);
			con.setDoOutput(true); //POST 데이터를 OutputStream으로 넘겨 주겠다는 설정 
			con.setUseCaches(false);
			con.setDefaultUseCaches(false);

			OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
			wr.write(json.toString()); //json 형식의 message 전달 
			wr.flush();

			StringBuilder sb = new StringBuilder();
			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				//Stream을 처리해줘야 하는 귀찮음이 있음.
				BufferedReader br = new BufferedReader(
						new InputStreamReader(con.getInputStream(), "utf-8"));
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line).append("\n");
				}
				br.close();
				System.out.println("" + sb.toString());
			} else {
				System.out.println(con.getResponseMessage());
			}
		} catch (Exception e){
			System.err.println(e.toString());
		}
}






    
}
