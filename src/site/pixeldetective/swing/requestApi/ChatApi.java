package site.pixeldetective.swing.requestApi;

import org.json.JSONArray;
import org.json.JSONObject;
import site.pixeldetective.swing.HttpConnector.HttpConnector;
import site.pixeldetective.swing.etc.Chat;
import site.pixeldetective.swing.etc.Room;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatApi {
    HttpConnector hc = new HttpConnector("/chat");

    public ArrayList<Chat> getChatList() {
        String chatList = hc.get(null);
        JSONArray jsonArray = new JSONArray(chatList);
        ArrayList<Chat> cList = new ArrayList<Chat>();
        // JSONArray의 각 요소를 반복 처리
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            // 각 JSONObject에서 필요한 데이터 추출
            int chatId = jsonObject.getInt("chat_id");
            String message = jsonObject.getString("message");
            String uName = jsonObject.getString("u_name");
            int sender = jsonObject.getInt("sender");
            String sendAt = jsonObject.getString("sent_at");
            Chat c = new Chat( chatId,  message,uName,  sender, convertStringToTimestamp(sendAt));
            cList.add(c);
        }
        return cList;
    }



    public String postChat(String message,String uName,int sender) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", message);
            jsonObject.put("u_name", uName);
            jsonObject.put("sender", sender + "");


            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Content-Type", "application/json; charset=UTF-8");

            String response = hc.post(jsonObject, headers);

            JSONObject jsonResponse = new JSONObject(response);
            String result = jsonResponse.getString("message");
            System.out.println("서버 응답: " + result);
            return "서버 응답: " + result;
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("서버와의 통신 중 오류가 발생했습니다.");
            return "서버 응답: " + "실패";
        }
    }




    public static Timestamp convertStringToTimestamp(String dateString)  {

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
            java.util.Date parsedDate = dateFormat.parse(dateString);
            return new Timestamp(parsedDate.getTime());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("convertStringToTimestamp" +"  메서드 관련 오류 ");
            return null;
        }

    }


    public static void main(String[] args) {
        ArrayList ls= new ChatApi().getChatList();
        System.out.println(ls);
        String aa = new ChatApi().postChat("helloworld13","kim",1 );




    }

}
