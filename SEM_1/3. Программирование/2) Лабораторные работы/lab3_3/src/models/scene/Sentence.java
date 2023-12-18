package models.scene;

import java.util.Objects;

public class Sentence {
    private final String content;

    public Sentence(String content) {
        this.content = content;
    }

    public void print() {
        System.out.println(content + "");
    }

    public void print(String punctuationMark) {
        System.out.println(content + punctuationMark);
    }

    public Sentence and(String text) {
        return new Sentence(content + "И вот" + text);
    }

    public Sentence but(String text) {
        return new Sentence(content + "и там " + text);
    }
    public Sentence them(String text) {
        return new Sentence(content + " тем " + text);
    }

    public Sentence whome(String text) {
        return new Sentence(content + " к " + text);
    }

    public Sentence with(String text) {
        return new Sentence(content + " со " + text);
    }

    public Sentence comma(String text) {
        return new Sentence(content + ", " + text);
    }

    public Sentence doubledot(String text) {
        return new Sentence(content + ": " + text);
    }

    public Sentence because(String text) {
        return new Sentence(content + ", чтобы " + text);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sentence sentence = (Sentence) o;
        return Objects.equals(content, sentence.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }


    @Override
    public String toString() {
        return "Sentence{" +
                "content='" + content + '\'' +
                '}';
    }

}
