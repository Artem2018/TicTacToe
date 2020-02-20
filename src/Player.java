import java.io.*;
import java.net.Socket;

public class Player implements Serializable {
    private static final long serialVersionUID = 2362136638811139274L;
    private int id;
    private transient Socket socket;

    public int getId() {
        return id;
    }

    public Socket getSocket() {
        return socket;
    }


    Player(int id, Socket socket){
        this.id = id;
        this.socket = socket;
    }
    public String toString(){
        return "Player # " + id;
    }
}
