package amtestr;

import java.util.ArrayList;
import java.util.List;

public class Test {
    private String name,author;
    private TestType type;
    private List<Question> questionList;

    public Test(String name, String author, TestType type) {
        this.name = name;
        this.author = author;
        this.type = type;
    }
    public void addQuestion(Question question){
        if(questionList == null) questionList = new ArrayList<>();
        questionList.add(question);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public TestType getType() {
        return type;
    }

    public void setType(TestType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("");
        builder.append(name);
        builder.append(" ");
        builder.append(author);
        builder.append(" ");
        builder.append(type.toString());
        builder.append("\n");
        for(Question question: questionList) {
            builder.append(question.getName());
            builder.append(" ");
            builder.append(question.getPreferredPosition());
            builder.append("\n");
            for(Answer answer: question.getAnswerList()) {
                builder.append(answer.getName());
                builder.append(" ");
                builder.append(answer.getPreferredPosition());
                builder.append(" ");
                builder.append(answer.isCorrect());
                builder.append("\n");
            }
        }
        return builder.toString();
    }
}
