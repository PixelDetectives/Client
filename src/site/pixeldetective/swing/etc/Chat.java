package site.pixeldetective.swing.etc;


import java.sql.Timestamp;

/**
 * chat_id	int	NO	PRI		auto_increment
 * message	text	NO
 * sender	int	NO	MUL
 * sent_at	timestamp	YES		CURRENT_TIMESTAMP	DEFAULT_GENERATED
 */
public class Chat {
    private int chatId;
    private String message;
    private String uName;
    private int sender;
    private Timestamp sentAt;

    public Chat(int chatId, String message,String uName, int sender, Timestamp sentAt) {
        this.chatId = chatId;
        this.message = message;
        this.uName = uName;
        this.sender = sender;
        this.sentAt = sentAt;
    }

    public Chat(String message, String uName, int sender, Timestamp sentAt) {
        this.message = message;
        this.uName = uName;
        this.sender = sender;
        this.sentAt = sentAt;
    }

    public Chat() {
    }

    @Override
    public String toString() {
        return "Chat{" +
                "chatId=" + chatId +
                ", message='" + message + '\'' +
                ", uName='" + uName + '\'' +
                ", sender=" + sender +
                ", sentAt=" + sentAt +
                '}';
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public Timestamp getSentAt() {
        return sentAt;
    }

    public void setSentAt(Timestamp sentAt) {
        this.sentAt = sentAt;
    }
}
