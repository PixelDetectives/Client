package site.pixeldetective.swing.webSocketClient;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import site.pixeldetective.swing.Frame.GameFrame;
import site.pixeldetective.swing.Panel.ChatPanel;
import site.pixeldetective.swing.Panel.GameChoicePanel;
import site.pixeldetective.swing.Panel.UserListPanel;
import site.pixeldetective.swing.etc.Game;
import site.pixeldetective.swing.etc.Room;
import site.pixeldetective.swing.etc.User;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import java.util.UUID;
public class SocketClient extends WebSocketClient {

    private static final Map<Integer, SocketClient> instances = new HashMap<>();
    private static final Object lock = new Object();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        support.addPropertyChangeListener(propertyName, listener);
    }

    public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        support.removePropertyChangeListener(propertyName, listener);
    }

    // 소켓 통신이 필요한 변수들을 선언
   public ChatPanel chatPanel;
   public UserListPanel userListPanel;
   public GameChoicePanel gameChoicePanel;
   public int currentUserSessionId1;

   public String jwt;

   public GameFrame gameFrame;
   public static String uName;
   public static int port;
   public static  String SERVER_URI = "ws://localhost:9001";
   //ws://localhost:8887/?u_id=someUserId&u_name=someUserName"
    private CompletableFuture<String> responseFuture;
    private static SocketClient instance;
    private SocketClient() throws URISyntaxException {
        super(new URI(SERVER_URI));;

    }
    public static SocketClient getInstance() {
        if (instance == null) {
            try {
                instance = new SocketClient();
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
        return instance;
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
        //System.out.println(jwt);

        try {
            JSONObject jsonObject = new JSONObject(receiveM); // 전체 메시지를 JSONObject로 파싱
            String type = jsonObject.getString("type");

            switch (type) {

                case "break":

                    break;
                case "newChatMessage":
                    System.out.println("newChat 실행");
                    // 첫 번째 파싱: 전체 JSON 문자열
                    String dataString = jsonObject.getString("data");
                    System.out.println(dataString);

                    // 두 번째 파싱: data 필드 내 JSON 문자열
                    JSONObject dataObject = new JSONObject(dataString);
                    System.out.println("nickname 파싱 실행 시작 ");
                    System.out.println(dataObject.getString("nickname"));
                    String nickname = dataObject.getString("nickname");
                    System.out.println("nickname 파싱 실행 끝 ");
                    System.out.println("message 파싱 실행 시작 ");
                    System.out.println(dataObject.getString("message"));
                    String message = dataObject.getString("message");
                    System.out.println("message 파싱 실행 끝 ");

                    String result = nickname + " " + message;

                    // chatPanel 객체의 appendToChatArea 메소드 호출
                    chatPanel.appendToChatArea(result);
                    chatPanel.updateUI();
                    System.out.println("최종 result " + result); // 디버깅용 출력
                    break;
                case "currentUsers":
                    System.out.println("currentUsers 실행");
                    String UserData = jsonObject.getString("data");
                    JSONArray usersArray = new JSONArray(UserData);
                    // 모든 유저 목록을 지운다.
                    SwingUtilities.invokeLater(() -> {
                        userListPanel.setEmpty();
                    });
                    Thread.sleep(10);
                    for (int j = 0; j < usersArray.length(); j++) {
                        System.out.println("반복문 실행");
                        String userDataStr = usersArray.getString(j);
                        JSONObject userData = new JSONObject(userDataStr);
                        String uId = userData.getString("uId");
                        if ("".equals(uId) || Objects.isNull(uId))
                            continue;
                        String uName = userData.getString("uName");
                        String status = userData.getString("status");
                        System.out.println("uId: " + uId + ", uName: " + uName + ", status: " + status);
                        // 여기서 유저 정보를 userListPanel 등에 추가하는 코드를 작성
                        System.out.println("socket에서 호출 ");
                        SwingUtilities.invokeLater(() -> {
                            userListPanel.setUserList(new User(1, uName, "join"));
                        });
                    }
                    SwingUtilities.invokeLater(() -> {
                        userListPanel.revalidate();
                        userListPanel.repaint();
                    });
                    break;

                case "currentRooms":
                    String RoomData = jsonObject.getString("data");
                    JSONArray roomsArray = new JSONArray(RoomData);

                    // 현재 룸 목록을 삭제
                    gameChoicePanel.setEmpty();
                    for (int j = 0; j < roomsArray.length(); j++) {
                        JSONObject userData = roomsArray.getJSONObject(j);
                        int r_id = userData.getInt("r_id");
                        String r_name = userData.getString("r_name");
                        int r_difficulty = userData.getInt("r_difficulty");

                        // 디버깅용 출력
                        System.out.println("r_id: " + r_id + ", r_name: " + r_name + ", r_difficulty: " + r_difficulty);

                        // Room 객체 생성 및 gameChoicePanel에 추가
                        Room room = new Room(r_id, r_difficulty, r_name);
                        gameChoicePanel.addRoom(room);
                    }
                    break;
                case "gameStart":
                    String data = jsonObject.getString("data");
                    JSONObject parseData = new JSONObject(data);
                    support.firePropertyChange("gameStart", null, parseData);
                    break;
                case "quickMatching":
                    System.out.println("quickMatching : " + jsonObject.toString());
                    String quickGameDataString = jsonObject.getString("data");
                    JSONObject quickGameData = new JSONObject(quickGameDataString);
                    support.firePropertyChange("quickStart", null, quickGameData);
                    break;
                case "pointResult":
                    String pointResultString = jsonObject.getString("data");
                    JSONObject pointResultData = new JSONObject(pointResultString);
                    support.firePropertyChange("pointResult", null, pointResultData);
                    break;
                case "gameResult":
                    String gameResultString = jsonObject.getString("data");
                    JSONObject gameResultData = new JSONObject(gameResultString);
                    support.firePropertyChange("gameResult", null, gameResultData);
                    break;
                default:
                    // 처리할 타입이 없을 경우의 로직
                    System.out.println(type + ":은 처리 할 수 있는 case(type)가 없어 !");
                    break;
            }
        } catch (JSONException e) {
            System.err.println("Error parsing JSON message: " + e.getMessage());
            System.out.println("-------");
            System.out.println(receiveM);
            System.out.println();
            System.out.println("--------");
            System.out.println("JSON타입이 이니여라");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
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
        if(!Objects.isNull(jwt)) {
            request.put("token", jwt);
        }
        responseFuture = new CompletableFuture<>();
        sendMessage(request);
        return responseFuture.get();
    }

    public void sendGetUserCount() throws Exception {
        JSONObject request = new JSONObject();
        request.put("command", "getUserCount");
        if(!Objects.isNull(jwt)) {
            request.put("token", jwt);
        }
        sendMessage(request);
    }

    public void sendChat(String nickname, String message) throws Exception {
        JSONObject request = new JSONObject();
        request.put("command", "sendChat");
        if(Objects.isNull(nickname))
            nickname ="";
        request.put("nickname", nickname);
        request.put("message", message);
        if(!Objects.isNull(jwt)) {
            request.put("token", jwt);
        }
        responseFuture = new CompletableFuture<>();
        sendMessage(request);
    }

    public void getCurrentUserList() throws Exception {
        JSONObject request = new JSONObject();
        request.put("command", "getCurrentUserList");
        if(!Objects.isNull(jwt)) {
            request.put("token", jwt);
        }
        sendMessage(request);
    }

    public void cancelMatching() throws Exception {
        JSONObject request = new JSONObject();
        request.put("command", "cancelMatching");
        if(!Objects.isNull(jwt)) {
            request.put("token", jwt);
        }
        sendMessage(request);
    }

    public void createRoom(String roomName, int difficulty) throws Exception {
        JSONObject request = new JSONObject();
        request.put("command", "createRoom");
        request.put("r_name", roomName);
        request.put("r_difficulty", difficulty);// 0,1.2
        if(!Objects.isNull(jwt)) {
            request.put("token", jwt);
        }
        sendMessage(request);
    }

    public void joinRoom(int r_id) throws Exception {
        JSONObject request = new JSONObject();
        request.put("command", "joinRoom");
        request.put("currentUserSessionId1", currentUserSessionId1);
        if(!Objects.isNull(jwt)) {
            request.put("token", jwt);
        }
        sendMessage(request);
    }

    public void getCurrentRoomList() throws Exception {
        JSONObject request = new JSONObject();
        request.put("command", "getCurrentRoomList");
        if(!Objects.isNull(jwt)) {
            request.put("token", jwt);
        }
        sendMessage(request);
    }

    public void deleteRoom(int r_id) throws Exception {
        JSONObject request = new JSONObject();
        request.put("r_id", r_id);
        request.put("command", "roomDelete");
        if(!Objects.isNull(jwt)) {
            request.put("token", jwt);
        }
        sendMessage(request);
    }

    public void getJwt(){


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
    public void quickMatching() throws Exception {
        JSONObject request = new JSONObject();
        request.put("command", "quickMatching");
        sendMessage(request);
    }
}
