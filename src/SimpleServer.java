import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {

    private static ServerSocket server = null;

    public SimpleServer() {
        boolean running = true;
        try {
            server = new ServerSocket(25252);
            System.out.println("Server Online\nWaiting for connections...");
            while (running) {
                Socket socket = server.accept();
                System.out.println("Connection received by:" + socket.getRemoteSocketAddress());

                ClientProcessing cli = new ClientProcessing(socket);
                cli.start();

            }
            server.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SimpleServer();
    }

}