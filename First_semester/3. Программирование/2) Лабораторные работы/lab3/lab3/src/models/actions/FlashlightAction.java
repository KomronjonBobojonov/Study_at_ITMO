package org.example.models.actions;

import org.example.interfaces.Action;

public class FlashlightAction implements Action {
    private final String characterName;

    public FlashlightAction(String characterName) {
        this.characterName = characterName;
    }

    @Override
    public String perform() {
        return characterName + " взяла карманный фонарик";
    }
}