import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Optional;

public class ClientChat extends Application {

    // declare and initialise the text display area
    private TextArea textWindow = new TextArea();
    private TextField inputWindow = new TextField();

    private OutputStream outStream;
    private DataOutputStream outDataStream;

    private ListenerTask listenerTask;

    private final int port = 8009;

    private String remoteMachine; // to hold the name chosen by the user
    private String name;

    @Override
    public void start(Stage stage) throws Exception {
        getInfo(); // call method that gets usesr name and server details
        startClientThread(); // start the client thread

        inputWindow.setOnKeyReleased(e ->
        {
            String text;

            if (e.getCode().getName().equals("Enter")) {
                text = name + " " + inputWindow.getText() + "\n"; //todo: \n is not working why? <- done by using setWrapText
                textWindow.appendText(text);
                inputWindow.setText(""); // display the texts typed in to the chat

                textWindow.setEditable(false);
                textWindow.setWrapText(true);

                try {
                    outDataStream.writeUTF(text);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });



        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(textWindow, inputWindow);
        Scene scene = new Scene(root, 500, 300);
        stage.setScene(scene);
        stage.setTitle(name);
        stage.show();
    }

    private void startClientThread() throws IOException {
        Socket connection;

        connection = new Socket("localhost", port);

        //create an output stream to the connection(socket)
        outStream = connection.getOutputStream();
        outDataStream = new DataOutputStream(outStream);

        // create a thread to listen for messages
        listenerTask = new ListenerTask(textWindow, connection);

        Thread thread = new Thread(listenerTask);
        thread.start();
    }

    private void getInfo(){

        Optional<String> response;

        // get user name
        TextInputDialog textDialog = new TextInputDialog();
        textDialog.setHeaderText("Enter user name");
        textDialog.setTitle("Chat Server");
        response = textDialog.showAndWait();
        name = response.get();

    }

    @Override
    public void stop()
    {
        System.exit(0); // terminate application when the window is closed
    }
    public static void main(String[] args)
    {
        launch(args);
    }
}
