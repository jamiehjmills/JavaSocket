import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ApplicationClientController {

    private String remoteHost;
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
    


    public void initialize(){

        connectingWithServer();

    }

    public void clickToCalculateNumbers(ActionEvent event){


    }


    public void connectingWithServer(){

        try{
            //attempt to create a connection to the server
            connection = new Socket(remoteHost, port);

            // create an input stream from the server
            inStream = connection.getInputStream();
            inDataStream = new DataInputStream(inStream);

            // create an output stream to the server
            outStream = connection.getOutputStream();
            outDataStream = new DataOutputStream(outStream);

            //send the host IP to the server
            outDataStream.writeUTF(connection.getLocalAddress().getHostAddress());

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
