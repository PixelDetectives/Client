package site.pixeldetective.swing.etc;

public class Room {

    private int rNum;
    private int gNum;
    private int rPlayer2;
    private int rPlayer1;
    private String rName;
    private String rDifficulty;

    public Room() {
    }

    public Room(int gNum, int rPlayer2, int rPlayer1, String rName, String rDifficulty) {
        this.gNum = gNum;
        this.rPlayer2 = rPlayer2;
        this.rPlayer1 = rPlayer1;
        this.rName = rName;
        this.rDifficulty = rDifficulty;
    }

    public Room(int rNum, int gNum, int rPlayer2, int rPlayer1, String rName, String rDifficulty) {
        this.rNum = rNum;
        this.gNum = gNum;
        this.rPlayer2 = rPlayer2;
        this.rPlayer1 = rPlayer1;
        this.rName = rName;
        this.rDifficulty = rDifficulty;
    }

    public int getrNum() {
        return rNum;
    }

    public void setrNum(int rNum) {
        this.rNum = rNum;
    }

    public int getgNum() {
        return gNum;
    }

    public void setgNum(int gNum) {
        this.gNum = gNum;
    }

    public int getrPlayer2() {
        return rPlayer2;
    }

    public void setrPlayer2(int rPlayer2) {
        this.rPlayer2 = rPlayer2;
    }

    public int getrPlayer1() {
        return rPlayer1;
    }

    public void setrPlayer1(int rPlayer1) {
        this.rPlayer1 = rPlayer1;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public String getrDifficulty() {
        return rDifficulty;
    }

    public void setrDifficulty(String rDifficulty) {
        this.rDifficulty = rDifficulty;
    }

    @Override
    public String toString() {
        return "Room{" +
                "rNum=" + rNum +
                ", gNum=" + gNum +
                ", rPlayer2=" + rPlayer2 +
                ", rPlayer1=" + rPlayer1 +
                ", rName='" + rName + '\'' +
                ", rDifficulty='" + rDifficulty + '\'' +
                '}';
    }
}
