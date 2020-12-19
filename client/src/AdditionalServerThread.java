import javafx.concurrent.Task;
import javafx.scene.control.TextArea;

import java.io.*;
import java.net.Socket;

public class AdditionalServerThread extends Task{

    private final Socket socket;
    private boolean connected = true;

    private InputStream inputStream;
    private DataInputStream dataInputStream;

    private TextArea textArea;

    public AdditionalServerThread(Socket socket, TextArea textArea) {

        this.socket = socket;
        this.textArea = textArea;

        try {
            // create an input stream from the remote machine
            inputStream = socket.getInputStream();
            dataInputStream = new DataInputStream(inputStream);

            // create a thread to listen for messages

        } catch (IOException e) {
        }
    }

    @Override
    protected Void call(){
        try {
            System.out.println("Connection established");


            while (connected) {

                String client = dataInputStream.readUTF();
                textArea.appendText(client);

            }

        } catch (IOException e) {
            e.printStackTrace();
            connected = false;
        }

        return null;
    }
}


