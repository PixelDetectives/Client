package site.pixeldetective.swing.requestApi;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import site.pixeldetective.swing.HttpConnector.HttpConnector;

public class SignUpApi {
    HttpConnector hc = new HttpConnector("/signup");
    JSONObject jo = new JSONObject();
    
    public boolean postSign(String id, String name, String pw) {
    	try {
    		jo.put("u_id", id);
        	jo.put("u_name", name);
        	jo.put("u_pw", pw);
        	
        	Map<String, String> headers = new HashMap<>();
	        headers.put("Content-Type", "application/json; charset=UTF-8");
	
	        String response = hc.post(jo, headers);
	
	        JSONObject jsonResponse = new JSONObject(response);
	        String message = jsonResponse.getString("message");
	        System.out.println("서버 응답: " + message);
    		return true;
    	}catch (Exception e) {
    		e.printStackTrace();
            System.out.println("서버와의 통신 중 오류가 발생했습니다.");
            
            return false;
		}
    	
    }
}

