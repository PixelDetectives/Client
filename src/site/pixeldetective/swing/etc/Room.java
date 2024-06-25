package site.pixeldetective.swing.etc;

public class Room {
    private String title;
    private String difficulty;

    public Room(String title, String difficulty) {
        this.title = title;
        this.difficulty = difficulty;
    }

    public String getTitle() {
        return title;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
