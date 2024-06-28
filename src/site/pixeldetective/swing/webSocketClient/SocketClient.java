package site.pixeldetective.swing.webSocketClient;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;
import site.pixeldetective.swing.Panel.ChatPanel;
import site.pixeldetective.swing.Panel.UserListPanel;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class SocketClient extends WebSocketClient {

    // 소켓 통신이 필요한 변수들을 선언
   public ChatPanel chatPanel;
   public UserListPanel userListPanel;




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
    public void onMessage(String message) {
        System.out.println("전달 받은 messase 시작 ");
        System.out.println(message);
        System.out.println("messase 끝 ");

        System.out.println(chatConvertor(message));
        chatPanel.appendToChatArea(chatConvertor(message));

        if (responseFuture != null) {
            responseFuture.complete(message);
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

    public void quickMatching() throws Exception {
        JSONObject request = new JSONObject();
        request.put("command", "quickMatching");
        sendMessage(request);
    }

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

    public void getCurrentRoomList() throws Exception {
        JSONObject request = new JSONObject();
        request.put("command", "getCurrentRoomList");
        sendMessage(request);
    }

    public void deleteRoom() throws Exception {
        JSONObject request = new JSONObject();
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
