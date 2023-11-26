package models.actions;

import interfaces.Action;

public class Was implements Action {
    private final String characterName;

    public Was(String characterName) {
        this.characterName = characterName;
    }
    @Override
    public String perform(){
        return "был " + characterName + ".";
    }
}
