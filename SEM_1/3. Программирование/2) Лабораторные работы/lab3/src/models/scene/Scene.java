package models.scene;

import interfaces.Tellable;
import models.charackter.AbsCharacter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Scene {
    private Tellable story;
    private Map<String, AbsCharacter> characters;

    public Scene() {
        this.characters = new HashMap<>();
    }

    public void play() {
        story.tell();
    }

    public AbsCharacter getCharacter(String name) {
        return characters.get(name);
    }

    public void addCharacter(AbsCharacter person) {
        characters.put(person.getName(), person);
    }

    public Tellable getStory() {
        return story;
    }

    public void setStory(Tellable story) {
        this.story = story;
    }

    public Map<String, AbsCharacter> getCharacters() {
        return characters;
    }

    public void setCharacters(Map<String, AbsCharacter> characters) {
        this.characters = characters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scene scene = (Scene) o;
        return Objects.equals(story, scene.story) && Objects.equals(characters, scene.characters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(story, characters);
    }

    @Override
    public String toString() {
        return "Scene{" +
                "story=" + story +
                ", characters=" + characters +
                '}';
    }
}
