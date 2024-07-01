import org.json.JSONObject;
import site.pixeldetective.swing.Frame.*;
import site.pixeldetective.swing.HttpConnector.HttpConnector;
import site.pixeldetective.swing.webSocketClient.SocketClient;

public class Main {
    public static void main(String[] args) {
        SocketClient.port = Integer.parseInt(args[0]);
        new UserFrame();

    }
}

