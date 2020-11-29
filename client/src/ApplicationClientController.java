import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Optional;

public class ApplicationClientController {

    private String localHost = "localhost";
    private int port;

    // declare low level and high level objects for input
    private InputStream inStream;
    private DataInputStream inDataStream;

    // declare low level and high level objects for output
    private OutputStream outStream;
    private DataOutputStream outDataStream;

    // declare a socket
    private Socket connection;

    @FXML
    Label sum;
    @FXML
    TextField firstNumber, secondNumber, msg;
    @FXML
    Button calculateButton;

    public void initialize() throws IOException {

        getPortNumber();
        connectingToServer();

    }

    @FXML
    public void clickToCalculateNumbers(ActionEvent event){

        try
        {
            // send the two integers to the server
            outDataStream.writeInt(Integer.parseInt(firstNumber.getText()));
            outDataStream.writeInt(Integer.parseInt(secondNumber.getText()));

            // read and display the result sent back from the server
            int result = inDataStream.readInt();
            sum.setText(""+result);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void connectingToServer() throws IOException {

            //attempt to create a connection to the server
            connection = new Socket(localHost, port);
            msg.setText("Connection Established");

            // create an input stream from the server
            inStream = connection.getInputStream();
            inDataStream = new DataInputStream(inStream);

            // create an output stream to the server
            outStream = connection.getOutputStream();
            outDataStream = new DataOutputStream(outStream);

            //send the host IP to the server
            outDataStream.writeUTF(connection.getLocalAddress().getHostAddress());

    }

    public void getPortNumber(){

        Optional<String> response;

        // use the TextInputDialog class to allow the user to enter port number
        TextInputDialog portDialog = new TextInputDialog();
        portDialog.setHeaderText("Enter port number");
        portDialog.setTitle("Addition Client");

        response = portDialog.showAndWait();
        port = Integer.valueOf(response.get());

    }

}
