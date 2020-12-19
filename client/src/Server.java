import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static javafx.application.Application.launch;

public class Server {

//    @Override
//    public void start(Stage primaryStage) throws Exception {
//
//        loadingFXML(primaryStage);
//
//    }

//    public void loadingFXML(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("ApplicationClient.fxml"));
//        primaryStage.setTitle("Application Client");
//        primaryStage.setScene(new Scene(root, 400, 400));
//        primaryStage.show();
//    }


    public static void main(String[] args) throws IOException {
        //launch(args);

        final int port = 8009;

        String client;
        Socket socket;
        AdditionalServerThread additionalServerThread;
        TextArea textArea;
        OutputStream outputStream;
        DataOutputStream dataOutputStream;


        ServerSocket serverSocket = new ServerSocket(8009);

        while (true) {
            socket = serverSocket.accept();

            //create an output stream to the socket
            outputStream = socket.getOutputStream();
            dataOutputStream = new DataOutputStream(outputStream);

            additionalServerThread = new AdditionalServerThread(socket, new TextArea());

            Thread thread = new Thread(additionalServerThread);
            thread.start();
        }

    }

}




