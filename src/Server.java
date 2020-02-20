import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

class Server {
    private AtomicInteger playerIdCounter = new AtomicInteger(1);
    private int id;
    private int port;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private Map<Integer,Player> PLAYERS = new HashMap<>();
    private PrintWriter out;
    private BufferedReader in;

    Server(int port){
        this.port = port;
    }

    @SuppressWarnings("InfiniteLoopStatement")
    void start(){
        try {
            serverSocket = new ServerSocket(port,8, InetAddress.getLocalHost());
            System.out.println("Server has started on port " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true){
            try {
                clientSocket = serverSocket.accept();
                System.out.println("Client: " + clientSocket.getInetAddress() + " has been connected " + clientSocket.toString());
                id = playerIdCounter.getAndIncrement();
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                Player player = new Player(id,clientSocket);
                ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
                PLAYERS.put(id,player);
                oos.writeObject(player);
                System.out.println(PLAYERS);
//                out.println(id);
//                System.out.println(id + " has been sent to client");
            } catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
