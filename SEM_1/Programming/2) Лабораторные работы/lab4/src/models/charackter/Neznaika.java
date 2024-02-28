package models.charackter;

import enums.Gengers;
import enums.Locations;
import enums.ShelfInVagons;
import environment.Person;
import interfaces.Readable;

import java.util.List;

public class Neznaika extends Person implements Readable {

    public Neznaika(String name, Gengers genger, Locations location, ShelfInVagons shelfInVagon) {
        super(name, genger, location, shelfInVagon);

    }

    @Override
    public void read(List<Person> passengers) {
        System.out.println("В то время как " + passengers.get(0).getName() +" и " + passengers.get(1).getName() + " удобно устроились на верхних полках, наблюдая за " +  "пассажирами,");
        if(passengers.get(2).getName() == "коротышка"){
             System.out.print("толстенький " + passengers.get(2).getName()+ " " + ShelfInVagons.BOTTOM + ", увлечённо читая газеты, ");
        }else{
            System.out.print(" худой " + passengers.get(3).getName() + ShelfInVagons.TOP + " увлечённо играл в телефоне,");
        }
    }
}