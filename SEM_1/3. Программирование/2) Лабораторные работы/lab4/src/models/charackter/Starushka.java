package models.charackter;

import enums.Gengers;
import enums.Locations;
import enums.ShelfInVagons;
import environment.Person;
import interfaces.Moveable;

public class Starushka extends Person implements Moveable {
    public Starushka(String name, Gengers genger, Locations location, ShelfInVagons shelfInVagon) {
        super(name, genger, location, shelfInVagon);
    }

    @Override
    public void move(String message) {
        System.out.print(message);
        System.out.print("и вскоре из неё выглянула голова любопытной кошки, ");
    }
}
