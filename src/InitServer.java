
import javax.swing.*;

public class InitServer {

   public static void main(String[] args) {
       int port;
       String jOptionPane = JOptionPane.showInputDialog(null,"Enter the port number"
               ,"Entering data",JOptionPane.QUESTION_MESSAGE);
        port = Integer.parseInt(jOptionPane);

       if (port < 1 || port > 65535) {
           System.out.println("Invalid port!");
           port = 9999;
       }
        Server server = new Server(port);
        server.start();
    }
}
