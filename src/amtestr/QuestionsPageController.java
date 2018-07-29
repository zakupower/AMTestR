package amtestr;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
        ScrollPane mainPane = (ScrollPane)((VBox)((Node)actionEvent.getSource()).getParent()).getChildren().get(2);
        validateFields(mainPane);
        addQuestionsToTest(mainPane);
        try {
            BorderPane root =Main.getRoot();
            VBox pane = FXMLLoader.load(getClass().getResource("questionspage.fxml"));
            root.setCenter(pane);
            ((Stage)root.getScene().getWindow()).setHeight(700);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void addQuestionsToTest(ScrollPane scrollPane){
        System.out.println(scrollPane);
        ObservableList<Node> questionNodes = (ObservableList<Node>)(((VBox)scrollPane.getContent()).getChildren());
        for(Node questionNode: questionNodes) {
            if(!(questionNode instanceof VBox)) continue;
            VBox questionVBox = (VBox) questionNode;
            HBox questionHBox = (HBox)questionVBox.getChildren().get(1);

            TextField questionNameField = (TextField)questionHBox.getChildren().get(0);
            TextField questionPreferredPosField = (TextField) questionHBox.getChildren().get(1);
            String questionName = questionNameField.getText();
            int preferredPos = questionPreferredPosField.getText().trim().isEmpty()?-1:Integer.parseInt(questionPreferredPosField.getText());

            List<Answer> answers = new ArrayList<>();
            for(int i = 4; i < questionVBox.getChildren().size()-1; i++) {
                HBox currentHBox = (HBox) questionVBox.getChildren().get(i);
                String answerName = ((TextField) currentHBox.getChildren().get(0)).getText();
                String answerPrefferedPosString = ((TextField)currentHBox.getChildren().get(1)).getText();
                int answerPreferredPos = answerPrefferedPosString.trim().isEmpty()?-1:Integer.parseInt(answerPrefferedPosString);
                boolean isCorrect = ((CheckBox)currentHBox.getChildren().get(2)).isSelected();
                answers.add(new Answer(answerName,answerPreferredPos,isCorrect));
            }
            Main.getTest().addQuestion(new Question(questionName,preferredPos,answers));
        }
        System.out.println(Main.getTest());
    }
    private void validateFields(ScrollPane scrollPane){

    }
}
