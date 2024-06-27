package site.pixeldetective.swing.requestApi;

import org.json.JSONObject;
import site.pixeldetective.swing.HttpConnector.HttpConnector;

import java.util.HashMap;
import java.util.Map;

// insert
public class MakeRoomAPI {
    HttpConnector hc = new HttpConnector("/room");

    public void postRoom(String roomName, String difficulty, int player1, int player2, int gameNum) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("r_name", roomName);
            jsonObject.put("r_difficulty", difficulty);
            jsonObject.put("r_player1", player1);
            jsonObject.put("r_player2", player2);
            jsonObject.put("g_num", gameNum);

            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json; charset=UTF-8");

            String response = hc.post(jsonObject, headers);

            JSONObject jsonResponse = new JSONObject(response);
            String message = jsonResponse.getString("message");
            System.out.println("서버 응답: " + message);
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("서버와의 통신 중 오류가 발생했습니다.");
        }
    }

}
