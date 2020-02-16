import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

 class Server {
    private int port;


    Server(int port){
        this.port = port;
    }

    @SuppressWarnings("InfiniteLoopStatement")
    void start(){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server has started on port " + port);
            while (true){
                try {
                    Socket socket = serverSocket.accept();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
