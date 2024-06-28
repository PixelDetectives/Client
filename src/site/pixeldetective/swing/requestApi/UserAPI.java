package site.pixeldetective.swing.requestApi;

import org.json.JSONObject;
import site.pixeldetective.swing.HttpConnector.HttpConnector;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UserAPI {
    HttpConnector hc = new HttpConnector("/login");

    public boolean postLogin(String uId, String uPw) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("u_id", uId);
            jsonObject.put("u_pw", uPw);
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Content-Type", "application/json; charset=UTF-8");
            String response = hc.post(jsonObject, headers);

            JSONObject jsonResponse = new JSONObject(response);
            String result = jsonResponse.getString("message");
            String jwt = jsonResponse.getString("jwt");
            // u_id u_name u_num / 로비프레임   검증


            if("성공".equals(result)){
                System.out.println(result);
                System.out.println(jwt);
                return true;
            }else {

                return false;
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("서버와의 통신 중 오류가 발생했습니다.");
            return false;
        }
    }





    public static void main(String[] args) {
        UserAPI ua = new UserAPI();
        ua.postLogin("test58","test58");
    }
}
