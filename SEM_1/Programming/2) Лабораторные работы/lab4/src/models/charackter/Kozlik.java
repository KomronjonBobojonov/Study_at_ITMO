package models.charackter;

import enums.Gengers;
import enums.Locations;
import enums.ShelfInVagons;
import environment.Person;
import interfaces.Attractable;

public class Kozlik extends Person implements Attractable {


    public Kozlik(String name, Gengers genger, Locations location, ShelfInVagons shelfInVagon) {
        super(name, genger, location, shelfInVagon);
    }
    private class Rost{
        public void entertoVagon(){
            System.out.println("Козлик вошёл в вагон");
        }
    }
    @Override
    public void attract(Person person) {
        System.out.println("которая тут же привлекла внимание " + person.getName() + ".");
    }
}
