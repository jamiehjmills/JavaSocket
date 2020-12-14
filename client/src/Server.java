import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        final int port = 8009;

        // declare a general socket and a server socket
        Socket socket;
        ServerSocket serverSocket;

        // declare low level and high level objects for input
        InputStream inStream;
        DataInputStream inDataStream;

        // declare low level and high level objects for output
        OutputStream outStream;
        DataOutputStream outDataStream;

        // declare other variables
        String client;
        boolean connected;

        // create a server socket
        serverSocket = new ServerSocket(port);
        System.out.println("Listening on port " + port);

        // listen for a connection from the client
        socket = serverSocket.accept();
        connected = true;
        System.out.println("connection with client has been established");

        // create an input stream from the client
        inStream = socket.getInputStream(); // Returns an input stream for this socket.
        inDataStream = new DataInputStream(inStream);

        // create an output stream to the client
        outStream = socket.getOutputStream();
        outDataStream = new DataOutputStream(outStream);

        // wait for a string from the client
        client = inDataStream.readUTF();
        System.out.println("Address of client: " + client);

        while (connected) {
            // read an integer from the client
            String clientText = inDataStream.readUTF();
            System.out.println("Chat received " + clientText);

            //send the reply to the client
            outDataStream.writeUTF("Received");
        }
    }
}


