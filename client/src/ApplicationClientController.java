import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

import java.io.*;
import java.net.Socket;
import java.util.Optional;

public class ApplicationClientController {

    private String localHost = "localhost";
    private int port = 8009;
    private String clientName;

    // declare low level and high level objects for input
    private InputStream inStream;
    private DataInputStream inDataStream;

    // declare low level and high level objects for output
    private OutputStream outStream;
    private DataOutputStream outDataStream;

    // declare a socket
    private Socket connection;

    @FXML
    TextField display, chat;
    @FXML
    Label name;
    @FXML
    Button enter;

    public void initialize() throws IOException {

        getName();
        connectingToServer();
        name.setText(clientName);

    }

    @FXML
    public void clickToSendTexts(ActionEvent event){

        try
        {
            //send the text to the server
            //outDataStream.writeUTF(firstNumber.getText()));

            // read and display the result sent back from the server
            int result = inDataStream.readInt();
            //sum.setText(""+result);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void connectingToServer() throws IOException {

            //attempt to create a connection to the server
            connection = new Socket(localHost, port);
            display.setText("Connection Established");

            // create an input stream from the server
            inStream = connection.getInputStream();
            inDataStream = new DataInputStream(inStream);

            // create an output stream to the server
            outStream = connection.getOutputStream();
            outDataStream = new DataOutputStream(outStream);

            //send the host IP to the server
            outDataStream.writeUTF(connection.getLocalAddress().getHostAddress());

    }

    public void getName(){

        Optional<String> response;

        // use the TextInputDialog class to allow the user to enter text
        TextInputDialog portDialog = new TextInputDialog();
        portDialog.setHeaderText("Enter your name");
        portDialog.setTitle("Client Details");

        response = portDialog.showAndWait();
        clientName = response.get();

    }

}
