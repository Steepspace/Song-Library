package songlib.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import songlib.view.SongLibController;

public class SongLib extends Application{

    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/songlib/view/SongLib.fxml"));
        AnchorPane root = (AnchorPane)loader.load();

		SongLibController songController = loader.getController();
		songController.start();

		Scene scene = new Scene(root, 400, 300);
		primaryStage.setScene(scene);
		primaryStage.show(); 

    }

    public static void main(String[] args) {
        launch(args);
    }
}