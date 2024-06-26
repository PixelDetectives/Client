import site.pixeldetective.swing.Frame.*;

public class Main {
    public static void main(String[] args) {
//        new GameFrame();
        MakeRoomFrame makeRoomFrame = new MakeRoomFrame();
        LobbyFrame lobbyFrame = new LobbyFrame();

        makeRoomFrame.lobbyFrame = lobbyFrame;
        lobbyFrame.makeRoomFrame = makeRoomFrame;
    }
}

