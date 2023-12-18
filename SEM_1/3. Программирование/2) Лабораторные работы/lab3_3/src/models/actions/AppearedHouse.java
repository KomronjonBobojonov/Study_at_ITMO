package models.actions;

import enums.Things;
import interfaces.Action;
import interfaces.Observer;

public class AppearedHouse implements Action {
    private final String characterName;
    private final Things whatToAppeared;

    public AppearedHouse(String characterName, Things whatToAppeared) {
        this.whatToAppeared = whatToAppeared;
        this.characterName = characterName;
    }
    @Override
    public String perform(){
        return " наконец показался " + whatToAppeared.getName() + " " + characterName;
    }
}