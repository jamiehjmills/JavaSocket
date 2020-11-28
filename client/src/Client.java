import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Client extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ApplicationClient.fxml"));
        primaryStage.setTitle("Application Client");
        primaryStage.setScene(new Scene(root, 400,400 ));
        primaryStage.show();

    }

    public static void main(String[] args) throws IOException {
        launch(args);

    }

}
