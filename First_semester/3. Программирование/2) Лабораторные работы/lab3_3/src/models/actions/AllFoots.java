package models.actions;

import interfaces.Observer;

public class AllFoots implements Observer {
    @Override
    public String observe() {
        return " всех ног к нему.";
    }
}
