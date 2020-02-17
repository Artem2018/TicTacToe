import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private String ip;
    private int port;
    private Socket socket;

    Client(String ip, int port){
        this.ip = ip;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public Socket getSocket() {
        return socket;
    }

    void start(){
        try {
            System.out.println(ip + " " + port);
            socket = new Socket(InetAddress.getLocalHost(),port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            System.out.println("the player with ip" + ip + "has been created");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
