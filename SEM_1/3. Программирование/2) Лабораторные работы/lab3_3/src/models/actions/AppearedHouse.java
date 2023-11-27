package models.actions;

import interfaces.Action;
import interfaces.Observer;

public class AppearedHouse implements Action {
    private final String characterName;

    public AppearedHouse(String characterName) {
        this.characterName = characterName;
    }
    @Override
    public String perform(){
        return " наконец показался домик " + characterName;
    }
}
