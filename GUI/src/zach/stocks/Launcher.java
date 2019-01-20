package zach.stocks;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import me.filewindow.FileWindowAPI;

import java.io.File;

public class Launcher extends Application {

    public static File[] files;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Launch.fxml"));
        primaryStage.setTitle("Stocks");
        primaryStage.setScene(new Scene(root, 800, 600));
        files = showDirectoryChooser(primaryStage);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


    public static File[] showDirectoryChooser(Stage stage) {
        DirectoryChooser directoryChooser = FileWindowAPI.openLoadDirectory("Choose a directory", stage);
        return directoryChooser.showDialog(stage).listFiles();
    }


}
