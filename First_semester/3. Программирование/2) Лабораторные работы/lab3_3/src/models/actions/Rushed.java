package models.actions;

import interfaces.Action;

public class Rushed implements Action {
    private final String characterName;

    public Rushed(String characterName) {
        this.characterName = characterName;
    }
    @Override
    public String perform(){
        return characterName + " бросился";
    }
}
