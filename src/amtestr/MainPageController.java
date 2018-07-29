package amtestr;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainPageController {
    
    public void fileNew(ActionEvent actionEvent) {
        BorderPane root = Main.getRoot();
        try {
            VBox vBox = FXMLLoader.load(getClass().getResource("filenewpage.fxml"));
            root.setCenter(vBox);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fileOpen(ActionEvent actionEvent) {
    }

    public void testPDF(ActionEvent actionEvent) {
    }

    public void testSolve(ActionEvent actionEvent) {
    }

    public void infoApp(ActionEvent actionEvent) {
    }

    public void infoAuthor(ActionEvent actionEvent) {
    }
}
