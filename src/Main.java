import org.json.JSONObject;
import site.pixeldetective.swing.Frame.*;
import site.pixeldetective.swing.HttpConnector.HttpConnector;

public class Main {
    public static void main(String[] args) {
//        new GameFrame();
        MakeRoomFrame makeRoomFrame = new MakeRoomFrame();
        LobbyFrame lobbyFrame = new LobbyFrame();

        makeRoomFrame.lobbyFrame = lobbyFrame;
        lobbyFrame.makeRoomFrame = makeRoomFrame;

        // HTTPConnector 생성 후 URL 경로 설정
//        HttpConnector hc = new HttpConnector("/game");
//        // 응답이 문자열로 날라옵니다.
//        String ret = hc.get(null);
//        // 문자열을 파싱해서 해야할 작업
//        System.out.println(ret);
    }
}

