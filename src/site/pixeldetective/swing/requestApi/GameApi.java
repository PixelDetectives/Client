package site.pixeldetective.swing.requestApi;

import org.json.JSONArray;
import org.json.JSONObject;
import site.pixeldetective.swing.HttpConnector.HttpConnector;
import site.pixeldetective.swing.etc.Chat;
import site.pixeldetective.swing.etc.Game;
import site.pixeldetective.swing.etc.Room;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class GameApi {
    HttpConnector hc = new HttpConnector("/game");

    public ArrayList<Game> getGameList() {
        String gameList = hc.get(null);

        JSONArray jsonArray = new JSONArray(gameList);
        ArrayList<Game> gList = new ArrayList<Game>();
        // JSONArray의 각 요소를 반복 처리
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            // 각 JSONObject에서 필요한 데이터 추출
            int gnum = jsonObject.getInt("g_num");
            String gimage1 = jsonObject.getString("g_image1");
            String gimage2 = jsonObject.getString("g_image2");
            String gname = jsonObject.getString("g_name");
            int gdifficulty = jsonObject.getInt("g_difficulty");


            Game g = new Game( gnum,  gimage1,  gimage2, gname, gdifficulty);
            gList.add(g);
        }
        return gList;
    }

    public static void main(String[] args) {
        ArrayList ls= new ChatApi().getChatList();
        System.out.println(ls);
    }

}
