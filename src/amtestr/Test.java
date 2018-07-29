package amtestr;

public class Test {
    private String name,author;
    private TestType type;

    public Test(String name, String author, TestType type) {
        this.name = name;
        this.author = author;
        this.type = type;
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
}
