package amtestr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FileNewPageController implements Initializable {
    @FXML
    private ComboBox<String> testType;
    @FXML
    private TextField testNameField,testAuthorField;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList testTypes = FXCollections.observableArrayList("Писмен Тест","Електронен Тест");
        testType.setItems(testTypes);
    }

    public void toQuestions(ActionEvent actionEvent) {
        if(testNameField.getText().trim().isEmpty()) {
            testNameField.requestFocus();
        } else if(testAuthorField.getText().trim().isEmpty()){
            testAuthorField.requestFocus();
        } else if(testType.getSelectionModel().isEmpty()) {
            testType.requestFocus();
        } else {
            createTest(testNameField.getText(),testAuthorField.getText(),testType.getSelectionModel().getSelectedIndex());
            BorderPane root = Main.getRoot();
            try {
                VBox pane = FXMLLoader.load(getClass().getResource("questionspage.fxml"));
                root.setCenter(pane);
                ((Stage)root.getScene().getWindow()).setHeight(700);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void createTest(String testName, String testAuthor, int testType) {
        Main.setTest(new Test(testName,testAuthor,testType==0?TestType.WRITTEN:TestType.ELECTRONIC));
    }
}
