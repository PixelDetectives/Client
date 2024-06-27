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
    }

}
