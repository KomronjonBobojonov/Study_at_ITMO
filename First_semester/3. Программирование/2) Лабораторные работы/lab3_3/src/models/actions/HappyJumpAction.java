package models.actions;

import interfaces.Action;
import interfaces.Observer;
import models.scene.Sentence;

public class HappyJumpAction implements Observer {
    private final String characterName;

    public HappyJumpAction(String characterName) {
        this.characterName = characterName;
    }

    @Override
    public String observe() {
        return characterName +  " тем временем весело прыгал впереди";
    }
}
