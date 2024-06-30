package site.pixeldetective.swing.webSocketClient;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import site.pixeldetective.swing.Panel.ChatPanel;
import site.pixeldetective.swing.Panel.GameChoicePanel;
import site.pixeldetective.swing.Panel.UserListPanel;
import site.pixeldetective.swing.etc.Room;
import site.pixeldetective.swing.etc.User;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class SocketClient extends WebSocketClient {

    // 소켓 통신이 필요한 변수들을 선언
   public ChatPanel chatPanel;
   public UserListPanel userListPanel;
   public GameChoicePanel gameChoicePanel;
   public int currentUserSessionId1;

   public String jwt;



    private static final String SERVER_URI = "ws://localhost:9001";
    private CompletableFuture<String> responseFuture;

    public SocketClient() throws URISyntaxException {
        super(new URI(SERVER_URI));
    }

    @Override
    public void onOpen(ServerHandshake handshake) {
        System.out.println("Connected to WebSocket server");
    }


    //서버로 부터 메세지가 오면 메세지를 가지고 type을 파악하고
    //해당되는 GUI의 메서드를 호출 해서 요소를 출력하거나 더한다.
    @Override
    public void onMessage(String receiveM) {

        System.out.println("Received message: " + receiveM);

        try {
            JSONObject jsonObject = new JSONObject(receiveM); // 전체 메시지를 JSONObject로 파싱
            String type = jsonObject.getString("type");

            switch (type) {
                case "newChat":
                    System.out.println("newChat 실행");
                    JSONObject dataObject = jsonObject.getJSONObject("data");
                    String nickname = dataObject.getString("nickname");
                    String message = dataObject.getString("message");
                    String result = nickname + " " + message;
                    chatPanel.appendToChatArea(result);
                    break;
                case "currentUsers" :
                    System.out.println("currentUsers 실행");
                    String UserData = jsonObject.getString("data");
                    JSONArray usersArray = new JSONArray(UserData);
                    //모든 유저 목록을 지운다.
                    userListPanel.removeAll();
                    for (int j = 0; j < usersArray.length(); j++) {
                        String userDataStr = usersArray.getString(j);
                        JSONObject userData = new JSONObject(userDataStr);
                        String uId = userData.getString("uId");
                        String uName = userData.getString("uName");
                        String status = userData.getString("status");
                        System.out.println("uId: " + uId + ", uName: " + uName + ", status: " + status);
                        // 여기서 유저 정보를 userListPanel 등에 추가하는 코드를 작성
                        userListPanel.setUserList(new User(1, uName, status));
                    }
                    break;
                case "currentRooms":
                    String RoomData = jsonObject.getString("data");
                    JSONArray roomsArray = new JSONArray(RoomData);

                    //현재 룸 목록을 삭제
                    //gameChoicePanel.setEmpty();
                    for (int j = 0; j < roomsArray.length(); j++) {
                        String roomDataStr = roomsArray.getString(j);
                        JSONObject userData = new JSONObject(roomDataStr);
                        String r_id = userData.getString("r_id");
                        String r_name = userData.getString("r_name");
                        String r_difficulty = userData.getString("r_difficulty");
                        // 여기서 유저 정보를 userListPanel 등에 추가하는 코드를 작성
                        System.out.println("r_id: " + r_id + ", r_name: " + r_name + ", r_difficulty: " + r_difficulty);

                        Room room = new Room(Integer.parseInt(r_id),Integer.parseInt(r_difficulty),r_name);
                        // 룸 패널에 룸을 추가
                        gameChoicePanel.addRoom(room);
                    }

                    break;
                default:
                    // 처리할 타입이 없을 경우의 로직
                    System.out.println(type + ":은 처리 할 수 있는 case(type)가 없어 !");
                    break;
            }
        } catch (JSONException e) {
            System.err.println("Error parsing JSON message: " + e.getMessage());
            System.out.println("JSON타입이 이니여라");
        }
    }


    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Disconnected from WebSocket server");
        if (responseFuture != null) {
            responseFuture.completeExceptionally(new Exception("WebSocket connection closed"));
        }
    }

    @Override
    public void onError(Exception ex) {
        System.out.println("WebSocket error: " + ex.getMessage());
        if (responseFuture != null) {
            responseFuture.completeExceptionally(ex);
        }
    }

    private void sendMessage(JSONObject request) throws Exception {
        if (isOpen()) {
            send(request.toString());
        } else {
            throw new Exception("WebSocket is not connected");
        }
    }

    public String sendHello() throws Exception {
        JSONObject request = new JSONObject();
        request.put("command", "hello");
        responseFuture = new CompletableFuture<>();
        sendMessage(request);
        return responseFuture.get();
    }

    public void sendGetUserCount() throws Exception {
        JSONObject request = new JSONObject();
        request.put("command", "getUserCount");
        sendMessage(request);
    }

    public String sendChatMessage(String nickname, String message) throws Exception {
        JSONObject request = new JSONObject();
        request.put("command", "sendChat");
        request.put("nickname", nickname);
        request.put("message", message);
        responseFuture = new CompletableFuture<>();
        sendMessage(request);
        return responseFuture.get();
    }

    public void getCurrentUserList() throws Exception {
        JSONObject request = new JSONObject();
        request.put("command", "getCurrentUserList");
        sendMessage(request);
    }

    // quickMatching기능 삭제 예정
//    public void quickMatching() throws Exception {
//        JSONObject request = new JSONObject();
//        request.put("command", "quickMatching");
//        sendMessage(request);
//    }

    public void cancelMatching() throws Exception {
        JSONObject request = new JSONObject();
        request.put("command", "cancleMatching");
        sendMessage(request);
    }

    public void createRoom(String roomName, int difficulty) throws Exception {
        JSONObject request = new JSONObject();
        request.put("command", "roomCreate");
        request.put("r_name", roomName);
        request.put("r_difficulty", difficulty);
        sendMessage(request);
    }

    public void joinRoom(int r_id) throws Exception {
        JSONObject request = new JSONObject();
        request.put("command", "joinRoom");
        request.put("currentUserSessionId1", currentUserSessionId1);
        sendMessage(request);
    }

    public void getCurrentRoomList() throws Exception {
        JSONObject request = new JSONObject();
        request.put("command", "getCurrentRoomList");
        sendMessage(request);
    }

    public void deleteRoom(int r_id) throws Exception {
        JSONObject request = new JSONObject();
        request.put("r_id", r_id);
        request.put("command", "roomDelete");
        sendMessage(request);
    }


    public static String chatConvertor(String jsonString) {
        try {


            JSONObject jsonObject = new JSONObject(jsonString);
            String type = jsonObject.getString("type");

            if ("newChatMessage".equals(type)) {

                String data = jsonObject.getString("data");
                JSONObject dataObject = new JSONObject(data);
                String nickname = dataObject.getString("nickname");
                String message = dataObject.getString("message");

                String result = nickname +"  "+message;
                return result;
            }
        } catch (Exception e) {

        }
        return null;
    }

}
