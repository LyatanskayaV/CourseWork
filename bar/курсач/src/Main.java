import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws IOException {
            Parent rootLayout = FXMLLoader.load(Main.class.getClass().getResource("/resources/view/authorize.fxml"));
            primaryStage.setScene(new Scene(rootLayout,450,200));
            primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

}