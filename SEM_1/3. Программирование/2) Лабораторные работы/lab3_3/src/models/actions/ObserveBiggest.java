package models.actions;

import interfaces.Observer;

public class ObserveBiggest implements Observer {
    @Override
    public String observe() {
        return "ОГРОМНЫМИ";
    }
}
