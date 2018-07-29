package amtestr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    private static BorderPane root;
    private static Test test;
    @Override
    public void start(Stage primaryStage) throws Exception{
        root = FXMLLoader.load(getClass().getResource("mainpage.fxml"));
        primaryStage.setTitle("AMTestR");
        primaryStage.setScene(new Scene(root, 500, 300));
        primaryStage.show();
    }
    public static BorderPane getRoot(){
        return root;
    }
    public static void setTest(Test test){
        Main.test = test;
    }
    public static Test getTest() {
        return test;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
