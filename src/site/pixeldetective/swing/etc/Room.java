package site.pixeldetective.swing.etc;

import java.sql.Timestamp;
import java.time.Instant;


// 방에서 일어 날 수 있는 모든 경우의 변수와 메서드를 적음
// rPlayer1는 방 만든 사람
// getCurrentTime 현재시간을 Timestamp로 반환
// getTimeDifferenceString 시간차를 반환
public class Room {

    private int rId;
    private int rDifficulty;        // 0,1,2
    private String rName;
    private Timestamp rStart;
    private Timestamp rEnd;
    private  int rPlayer1;          // 방만든 사람.
    private  int rPlayer2;
    private  int rP1Hit;
    private  int rP1Miss;
    private  int rP2Hit;
    private  int rP2Miss;


    public Room(int rId, int rDifficulty, String rName, Timestamp rStart, Timestamp rEnd, int rPalyer1, int rPlayer2, int rP1Hit, int rP1Miss, int rP2Hit, int rP2Miss) {
        this.rId = rId;
        this.rDifficulty = rDifficulty;
        this.rName = rName;
        this.rStart = rStart;
        this.rEnd = rEnd;
        this.rPlayer1 = rPalyer1;
        this.rPlayer2 = rPlayer2;
        this.rP1Hit = rP1Hit;
        this.rP1Miss = rP1Miss;
        this.rP2Hit = rP2Hit;
        this.rP2Miss = rP2Miss;
    }


    @Override
    public String toString() {
        return "Room{" +
                "rId=" + rId +
                ", rDifficulty=" + rDifficulty +
                ", rName='" + rName + '\'' +
                ", rStart=" + rStart +
                ", rEnd=" + rEnd +
                ", rPalyer1=" + rPlayer1 +
                ", rPlayer2=" + rPlayer2 +
                ", rP1Hit=" + rP1Hit +
                ", rP1Miss=" + rP1Miss +
                ", rP2Hit=" + rP2Hit +
                ", rP2Miss=" + rP2Miss +
                '}';
    }

    public Room(int rId, int rDifficulty, String rName ) {
        this.rId = rId;
        this.rDifficulty = rDifficulty;
        this.rName = rName;
    }


    public Room() {
    }

    public int getrId() {
        return rId;
    }

    public void setrId(int rId) {
        this.rId = rId;
    }

    public int getrDifficulty() {
        return rDifficulty;
    }

    public void setrDifficulty(int rDifficulty) {
        this.rDifficulty = rDifficulty;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public Timestamp getrStart() {
        return rStart;
    }

    public void setrStart(Timestamp rStart) {
        this.rStart = rStart;
    }

    public Timestamp getrEnd() {
        return rEnd;
    }

    public void setrEnd(Timestamp rEnd) {
        this.rEnd = rEnd;
    }

    public int getrPlayer1() {
        return rPlayer1;
    }

    public void setrPlayer1(int rPlayer1) {
        this.rPlayer1 = rPlayer1;
    }

    public int getrPlayer2() {
        return rPlayer2;
    }

    public void setrPlayer2(int rPlayer2) {
        this.rPlayer2 = rPlayer2;
    }

    public int getrP1Hit() {
        return rP1Hit;
    }

    public void setrP1Hit(int rP1Hit) {
        this.rP1Hit = rP1Hit;
    }

    public int getrP1Miss() {
        return rP1Miss;
    }

    public void setrP1Miss(int rP1Miss) {
        this.rP1Miss = rP1Miss;
    }

    public int getrP2Hit() {
        return rP2Hit;
    }

    public void setrP2Hit(int rP2Hit) {
        this.rP2Hit = rP2Hit;
    }

    public int getrP2Miss() {
        return rP2Miss;
    }

    public void setrP2Miss(int rP2Miss) {
        this.rP2Miss = rP2Miss;
    }

    // 두 개의 Timestamp 객체 간의 시간 차이를 분:초 형태의 문자열로 반환하는 메서드
    public static String getTimeDifferenceString(Timestamp timestamp1, Timestamp timestamp2) {
        // 시간 차이를 밀리초 단위로 계산
        long differenceInMillis = Math.abs(timestamp2.getTime() - timestamp1.getTime());

        // 밀리초를 초로 변환
        long totalSeconds = differenceInMillis / 1000;

        // 초를 분과 초로 변환
        long minutes = totalSeconds / 60;
        long seconds = totalSeconds % 60;

        // 분:초 형태의 문자열로 반환
        return String.format("%d:%02d", minutes, seconds);
    }

    public static Timestamp getCurrentTime(){
        Instant now = Instant.now();
        // 현재 시간을 Timestamp로 변환
        Timestamp timestamp = Timestamp.from(now);

        return timestamp;
    }

}
