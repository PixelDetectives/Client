package site.pixeldetective.swing.etc;

public class User {

    int u_id;
    String uName;

    String status;


    public User(int u_id, String uName, String status) {
        this.u_id = u_id;
        this.uName = uName;
        this.status = status;
    }


    public User() {
    }


    @Override
    public String toString() {
        return "User{" +
                "u_id=" + u_id +
                ", uName='" + uName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }


    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
