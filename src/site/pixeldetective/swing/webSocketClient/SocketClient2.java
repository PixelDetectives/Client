package site.pixeldetective.swing.webSocketClient;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

import javax.swing.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CompletableFuture;

public class SocketClient2 extends WebSocketClient {

    private static final String SERVER_URI = "ws://localhost:9001";
    private CompletableFuture<String> responseFuture;
    private JDialog matchingDialog;

    public SocketClient2() throws URISyntaxException {
        super(new URI(SERVER_URI));
    }

    public void setMatchingDialog(JDialog matchingDialog) {
        this.matchingDialog = matchingDialog;
    }

    @Override
    public void onOpen(ServerHandshake handshake) {
        System.out.println("Connected to WebSocket server");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("Received message: " + message);
        if (responseFuture != null) {
            responseFuture.complete(message);
        }

        JSONObject jsonMessage = new JSONObject(message);
//        String command = jsonMessage.getString("command");
//
//        if ("matchFound".equals(command)) {
//            if (matchingDialog != null) {
//                matchingDialog.dispose();
//            }
//            startGame();
//        }
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

    // createRoom보냄 - 매칭중 대기중 - 누가 들어옴 - onMessage (게임접속,r_id)
    // 만든방 접속 (r_id ==) - 게임시작

    public void createRoom(String roomName, int difficulty) throws Exception {
        JSONObject request = new JSONObject();
        request.put("command", "createRoom");
        request.put("r_name", roomName);
        request.put("r_difficulty", difficulty);
//        sendMessage(request);
    }

    public void waitForMatch() {
        // 서버에서 매칭이 완료될 때까지 대기
        // 매칭이 완료되면 서버에서 "matchFound" 메시지를 보내도록 설정
    }

    public void cancelMatching() throws Exception {
        JSONObject request = new JSONObject();
        request.put("command", "cancelMatching");
        sendMessage(request);
        if (matchingDialog != null) {
            matchingDialog.dispose();
        }
    }

    private void startGame() {
        // 게임 시작 로직을 여기에 추가
        System.out.println("게임이 시작됩니다!");
    }

//    public static void main(String[] args) throws Exception {
//        SocketClient2 sc = new SocketClient2();
//        sc.connect();
//        Thread.sleep(1000);
//        sc.createRoom("아주쉬운방", 1);
//    }
}
