package environment;

import enums.Gengers;
import enums.Locations;
import enums.ShelfInVagons;

public class Person extends Essence {

    public Gengers gender;
    public ShelfInVagons shelfInVagon;
    public Locations location;

    public Person(String name, Gengers genger, Locations location, ShelfInVagons shelfInVagon) {
        super(name);
        this.gender = genger;
        this.shelfInVagon = shelfInVagon;
        this.location = location;
    }
}
