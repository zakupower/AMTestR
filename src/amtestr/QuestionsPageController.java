package amtestr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class QuestionsPageController implements Initializable {
    @FXML
    TextField firstQuestionField, firstQuestionPreferredField,
                firstQuestionFirstAnswerField, firstQuestionFirstAnswerPreferredField;
    @FXML
    Label testNameLabel, testAuthorLabel;

    @FXML
    public void deleteQuestion(ActionEvent actionEvent) {
        Node thisQuestion = ((Node)actionEvent.getSource()).getParent().getParent();
        //request focus on question name before this question
        VBox vBox = (VBox)thisQuestion.getParent();
        int indexToFocus = vBox.getChildren().indexOf(thisQuestion)-1;
        VBox questionVBox = (VBox)vBox.getChildren().get(indexToFocus);
        HBox questionHBox = (HBox)questionVBox.getChildren().get(1);
        TextField question = (TextField) questionHBox.getChildren().get(0);
        question.requestFocus();

        ((Pane)thisQuestion.getParent()).getChildren().remove(thisQuestion);
    }
    @FXML
    public void deleteAnswer(ActionEvent actionEvent) {
        Node thisAnswer = ((Node)actionEvent.getSource()).getParent();
        //request focus on question name
        VBox vBox = (VBox) thisAnswer.getParent();
        HBox questionHBox = (HBox)vBox.getChildren().get(1);
        TextField questionField = (TextField) questionHBox.getChildren().get(0);
        questionField.requestFocus();

        ((Pane)thisAnswer.getParent()).getChildren().remove(thisAnswer);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        testNameLabel.setText(Main.getTest().getName());
        testAuthorLabel.setText(Main.getTest().getAuthor());
    }

    public void addAnswer(ActionEvent actionEvent) {
        Pane thisQuestion = (Pane)((Node) actionEvent.getSource()).getParent();
        thisQuestion.getChildren().add(thisQuestion.getChildren().size()-1,createAnswerHBox());
    }
    private HBox createAnswerHBox(){
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);
        TextField answer = new TextField();
        answer.setPrefWidth(300);
        TextField preferredPos = new TextField();
        preferredPos.setPrefWidth(30);
        CheckBox checkBox = new CheckBox("");
        Button deleteButton = new Button("X");
        deleteButton.getStyleClass().add("roundButtonAnswer");
        deleteButton.setOnAction(this::deleteAnswer);
        hBox.getChildren().addAll(answer,preferredPos,checkBox,deleteButton);
        return hBox;
    }
    public void addQuestion(ActionEvent actionEvent) {
        Pane questionPane = (Pane)((Node) actionEvent.getSource()).getParent();
        questionPane.getChildren().add(questionPane.getChildren().size()-1,createQuestion());
    }

    private VBox createQuestion(){
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10,10,10,10));
        vBox.getStyleClass().add("questionBox");
        Label questionLabel = new Label("Въпрос:");
        HBox questionHBox = new HBox();
        questionHBox.setAlignment(Pos.CENTER);
        questionHBox.setSpacing(10);
        TextField question = new TextField();
        question.setPrefWidth(300);
        TextField preferredPos = new TextField();
        preferredPos.setPrefWidth(30);
        Button deleteButton = new Button("X");
        deleteButton.setOnAction(this::deleteQuestion);
        deleteButton.getStyleClass().add("roundButtonQuestion");
        Separator separator = new Separator();
        separator.setPrefWidth(200);
        Label answerLabel = new Label("Отговори:");
        HBox answerHBox = createAnswerHBox();
        Button addAnswerButton = new Button("Добави отговор");
        addAnswerButton.setOnAction(this::addAnswer);
        questionHBox.getChildren().addAll(question,preferredPos,deleteButton);
        vBox.getChildren().addAll(questionLabel,questionHBox,separator,answerLabel,answerHBox,addAnswerButton);
        return vBox;
    }


    public void toScoring(ActionEvent actionEvent) {

    }
}
