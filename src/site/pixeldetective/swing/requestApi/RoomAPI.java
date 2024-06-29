package site.pixeldetective.swing.requestApi;

import org.json.JSONArray;
import org.json.JSONObject;
import site.pixeldetective.swing.HttpConnector.HttpConnector;
import site.pixeldetective.swing.etc.Room;

import java.util.ArrayList;


// 소켓으로 통신할 것이라 더 이상 필요 x 추후에 삭제하겠음.
public class RoomAPI {
//    HttpConnector hc = new HttpConnector("/room");
//
//    public ArrayList<Room> getRoomList(){
//        String roomList = hc.get(null);
//
//        JSONArray jsonArray = new JSONArray(roomList);
//        ArrayList<Room> rList = new ArrayList<Room>();
//        // JSONArray의 각 요소를 반복 처리
//        for (int i = 0; i < jsonArray.length(); i++) {
//            JSONObject jsonObject = jsonArray.getJSONObject(i);
//            // 각 JSONObject에서 필요한 데이터 추출
//            int gNum = jsonObject.getInt("g_num");
//            int rPlayer2 = jsonObject.getInt("r_player2");
//            int rNum = jsonObject.getInt("r_num");
//            String rDifficulty = jsonObject.getString("r_difficulty");
//            int rPlayer1 = jsonObject.getInt("r_player1");
//            String rName = jsonObject.getString("r_name");
//            Room r = new Room(rNum, gNum,  rPlayer2,  rPlayer1, rName, rDifficulty);
//            rList.add(r);
//        }
//        return rList;
//    }

//    public static void main(String[] args) {
//        new RoomAPI(). getRoomList();
//    }
}
