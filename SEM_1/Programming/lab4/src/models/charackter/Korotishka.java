package models.charackter;

import enums.Gengers;
import enums.Locations;
import enums.ShelfInVagons;
import environment.Person;
import interfaces.SetDownable;

public class Korotishka extends Person implements SetDownable {
    public Korotishka(String name, Gengers genger, Locations location, ShelfInVagons shelfInVagon) {
        super(name, genger, location, shelfInVagon);
    }

    @Override
    public void set(Person person) {
        if (person.getName() == "старушка") {
            System.out.println("не заметил, как рядом с ним уселась " + person.getName() + " с большой корзиной.");
        }else{
            System.out.println("продолжил играть, пока " + person.getName() + "не попросила, встать и помочь ей");
        }
    }
}
