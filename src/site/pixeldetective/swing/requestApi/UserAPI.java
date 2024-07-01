package site.pixeldetective.swing.requestApi;

import org.json.JSONObject;
import site.pixeldetective.swing.Frame.UserFrame;
import site.pixeldetective.swing.HttpConnector.HttpConnector;
import site.pixeldetective.swing.Panel.ChatPanel;
import site.pixeldetective.swing.webSocketClient.SocketClient;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UserAPI {
    HttpConnector hc = new HttpConnector("/login");


    public boolean postLogin(String uId, String uPw, UserFrame userFrame) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("u_id", uId);
            jsonObject.put("u_pw", uPw);
            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Content-Type", "application/json; charset=UTF-8");
            System.out.println("before send");
            String response = hc.post(jsonObject, headers);
            System.out.println("after send");
            JSONObject jsonResponse = new JSONObject(response);
            System.out.println("response : " + jsonResponse.toString());
            String result = jsonResponse.getString("message");

            // u_id u_name u_num / 로비프레임   검증
            

            if("성공".equals(result)){
                System.out.println("로그인 요청 결과 성공");
                System.out.println(result);
                userFrame.jwt = jsonResponse.getString("jwt");


                ChatPanel.nickName = jsonResponse.getString("nickName");
                //userFrame.lf.lp.panel2.updateUI();
                System.out.println(userFrame.jwt);
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
        String aa = null;
       // ua.postLogin("kim1234","1234",aa);
        System.out.println("jwt:"+aa);
    }
}
