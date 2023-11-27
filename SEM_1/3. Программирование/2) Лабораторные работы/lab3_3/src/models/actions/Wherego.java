package models.actions;

import interfaces.Action;

public class Wherego implements Action {
    @Override
    public String perform(){
        return "\"Сюда идти?\"";
    }
}
