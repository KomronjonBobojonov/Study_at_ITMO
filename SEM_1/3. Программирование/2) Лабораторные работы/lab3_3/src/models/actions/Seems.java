package models.actions;

import enums.Sizes;
import interfaces.Action;

public class Seems implements Action {
    private String characterName;
    private  Sizes size;

    public Seems(String characterName, Sizes size) {
        this.characterName = characterName;
        this.size = size;
    }

    @Override
    public String perform() {
        return size.getName() + " кажится " + characterName;
    }
}
