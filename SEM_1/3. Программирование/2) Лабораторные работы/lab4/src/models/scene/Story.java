package models.scene;

import interfaces.Tellable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Story implements Tellable {
    private List<Sentence> sentences;

    public Story() {
        this.sentences = new ArrayList<>(Collections.emptyList());
    }

    @Override
    public void tell() {
        for (var sentence : sentences) {
            sentence.print();
        }
    }

    public void addSentence(Sentence sentence) {
        sentences.add(sentence);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Story story = (Story) o;
        return Objects.equals(sentences, story.sentences);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sentences);
    }

    @Override
    public String toString() {
        return "Story{" +
                "sentences=" + sentences +
                '}';
    }

}
