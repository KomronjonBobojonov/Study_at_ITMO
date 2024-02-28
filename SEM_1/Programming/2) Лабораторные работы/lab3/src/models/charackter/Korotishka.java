package models.charackter;

import enums.Gengers;
import enums.Locations;
import enums.ShelfInVagons;
import environment.Person;

public class Korotishka extends Person {
    public Korotishka(String name, Gengers genger, Locations location, ShelfInVagons shelfInVagon) {
        super(name, genger, location, shelfInVagon);
    }
}
