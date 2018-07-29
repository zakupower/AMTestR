package amtestr;

import java.util.List;

public class Question {
    private String name;
    private int preferredPosition;
    private List<Answer> answerList;

    public Question(String name, int preferredPosition, List<Answer> answerList) {
        this.name = name;
        this.preferredPosition = preferredPosition;
        this.answerList = answerList;
    }
    public void addAnswer(Answer answer) {
        answerList.add(answer);
    }
    public String getName() {
        return name;
    }

    public int getPreferredPosition() {
        return preferredPosition;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }
}
