package site.pixeldetective.swing.requestApi;

import org.json.JSONArray;
import org.json.JSONObject;
import site.pixeldetective.swing.HttpConnector.HttpConnector;
import site.pixeldetective.swing.etc.Room;

import java.util.ArrayList;

public class RoomAPI {
    HttpConnector hc = new HttpConnector("/room");

    public ArrayList<Room> getRoomList(){
        String roomList = hc.get(null);

        JSONArray jsonArray = new JSONArray(roomList);


        // JSONArray의 각 요소를 반복 처리
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            // 각 JSONObject에서 필요한 데이터 추출
            int gNum = jsonObject.getInt("g_num");
            int rPlayer2 = jsonObject.getInt("r_player2");
            int rNum = jsonObject.getInt("r_num");
            String rDifficulty = jsonObject.getString("r_difficulty");
            int rPlayer1 = jsonObject.getInt("r_player1");
            String rName = jsonObject.getString("r_name");

            // 추출한 데이터를 출력
            //System.out.println("Game Number: " + gNum);
            System.out.println("Player 2: " + rPlayer2);
            System.out.println("Round Number: " + rNum);
            System.out.println("Difficulty: " + rDifficulty);
            System.out.println("Player 1: " + rPlayer1);
            System.out.println("Round Name: " + rName);
        }

        return null;

    }

    public static void main(String[] args) {
        new RoomAPI(). getRoomList();
    }
}
