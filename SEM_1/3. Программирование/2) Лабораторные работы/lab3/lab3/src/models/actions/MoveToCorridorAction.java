package org.example.models.actions;

import org.example.enums.Place;
import org.example.interfaces.Movable;

public class MoveToCorridorAction implements Movable {
    @Override
    public String moveTo(Place place) {
        return place == Place.CORRIDOR ? "вышла в коридор" : "";
    }
}