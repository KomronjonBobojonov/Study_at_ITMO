package models.actions;

import enums.MeanTime;
import enums.Things;
import interfaces.Action;
import interfaces.Observer;
import models.scene.Sentence;

import java.sql.Time;

public class HappyJumpAction implements Observer {
    private final String characterName;
    private final MeanTime TimeT;
    public HappyJumpAction(String characterName, MeanTime TimeT) {
        this.TimeT = TimeT;
        this.characterName = characterName;
    }

    @Override
    public String observe() {
        return characterName + " " + TimeT.getName() + " весело прыгал впереди";
    }
}
