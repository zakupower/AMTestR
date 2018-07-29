package amtestr;

public class Answer {
    private String name;
    private int preferredPos;
    private boolean correct;

    public Answer(String name, int preferredPos, boolean correct) {
        this.name = name;
        this.preferredPos = preferredPos;
        this.correct = correct;
    }

    public String getName() {
        return name;
    }

    public int getPreferredPosition() {
        return preferredPos;
    }

    public boolean isCorrect() {
        return correct;
    }
}
