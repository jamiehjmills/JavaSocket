import javafx.concurrent.Task;
import javafx.scene.control.TextArea;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ListenerTask extends Task {

    private InputStream inputStream;
    private DataInputStream dataInputStream;
    private TextArea window;
    private Socket connection;

    public ListenerTask(TextArea window, Socket connection) {

        this.window = window;
        this.connection = connection;

        try {
            inputStream = connection.getInputStream();
            dataInputStream = new DataInputStream(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected Void call() throws Exception {
        String msg;

        while (true) {
            try {
                msg = dataInputStream.readUTF(); // read the incoming message
                window.appendText(msg);
            } catch (IOException e) {
            }

        }

    }

}
