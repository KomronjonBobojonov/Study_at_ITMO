package models.actions;

import interfaces.Action;

public class Ask implements Action {
    @Override
    public String perform(){
        return "спросить";
    }
}
