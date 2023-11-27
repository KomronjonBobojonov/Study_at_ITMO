package org.example.models.actions;

import org.example.interfaces.Observer;

public class ObserveCorridorAction implements Observer {
    @Override
    public String observe() {
        return "Там никого не было";
    }
}