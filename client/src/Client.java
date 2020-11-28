import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Application {

//    private String remoteHost;
//    private int port;
//
//    // declare low level and high level objects for input
//    private InputStream inStream;
//    private DataInputStream inDataStream;
//
//    // declare low level and high level objects for output
//    private OutputStream outStream;
//    private DataOutputStream outDataStream;
//
//    // declare a socket
//    private Socket connection;

    @Override
    public void start(Stage primaryStage) throws Exception {

        loadingFXML(primaryStage);
        //connectingWithServer();

    }

    public void loadingFXML(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ApplicationClient.fxml"));
        primaryStage.setTitle("Application Client");
        primaryStage.setScene(new Scene(root, 400,400 ));
        primaryStage.show();
    }

//    public void connectingWithServer(){
//
//      try{
//          //attempt to create a connection to the server
//          connection = new Socket(remoteHost, port);
//
//          // create an input stream from the server
//          inStream = connection.getInputStream();
//          inDataStream = new DataInputStream(inStream);
//
//          // create an output stream to the server
//          outStream = connection.getOutputStream();
//          outDataStream = new DataOutputStream(outStream);
//
//          //send the host IP to the server
//          outDataStream.writeUTF(connection.getLocalAddress().getHostAddress());
//
//      } catch (UnknownHostException e) {
//          e.printStackTrace();
//      } catch (IOException e) {
//          e.printStackTrace();
//      }
//
//
//    }

    public static void main(String[] args) throws IOException {
        launch(args);

    }

}
