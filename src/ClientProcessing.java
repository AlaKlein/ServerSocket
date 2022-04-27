import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClientProcessing extends Thread {

    private Socket socket = null;
    private static DataInputStream in = null;
    private static DataOutputStream out = null;

    public ClientProcessing(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {

            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());

            out.writeUTF(
                    "Welcome to my Server!");

            boolean running = true;
            while (running) {

                String command = in.readUTF(); // --&gt; usar no android
                // String command = in.readLine(); // --&gt; usar no telnet

                System.out.println("Server receiveid: " + command + "\n");

                if (command.equalsIgnoreCase("exit")) {
                    out.writeUTF("By....");
                    break;
                } else {
                    out.writeUTF(command + " was received!");
                }
            }
            socket.close();

            System.out.println("End of Server");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}