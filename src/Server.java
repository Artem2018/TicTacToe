import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

 class Server {
    private int port;
    private ServerSocket serverSocket;
    private Socket clientSocket;

    Server(int port){
        this.port = port;
    }

    @SuppressWarnings("InfiniteLoopStatement")
    void start(){
        try {
            serverSocket = new ServerSocket(9999,8, InetAddress.getLocalHost());
            System.out.println("Server has started on port " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true){
            try {
                clientSocket = serverSocket.accept();
                System.out.println(" Client: " + clientSocket.getInetAddress() + " has been connected" + clientSocket.toString());
                PrintWriter out =
                        new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
