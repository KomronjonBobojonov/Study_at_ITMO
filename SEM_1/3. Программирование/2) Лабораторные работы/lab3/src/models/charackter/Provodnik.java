package models.charackter;

import enums.Gengers;
import enums.Locations;
import enums.ShelfInVagons;
import environment.Person;
import interfaces.Checkable;
import interfaces.Saidable;

import java.util.Objects;

import static enums.ShelfInVagons.TOP;

public class Provodnik extends Person implements Checkable, Saidable {
    public Provodnik(String name, Gengers genger, Locations location, ShelfInVagons shelfInVagon) {
        super(name, genger, location, shelfInVagon);
    }

    private boolean ticket = true;
    private boolean checker = true;

    @Override
    public void check(String nameOfPerson) {
        if (ticket){
            checker = true;
            System.out.print(this.name +" "+ "проверил" + " их " + "билет");
        }else{
            ticket = false;
            checker = false;
            System.out.print(this.name +" "+ "не впустил их, так как у них отсутствует билет");
        }
    }

    @Override
    public void said(Person person) {
        if (checker){
            System.out.print(" и сказал,");
        }else{
            System.out.print(" вежливо попросил освободить место");
        }
        if(Objects.equals(person.shelfInVagon, TOP)){
            System.out.println(" что оба их места на верхних полках.");
        }else{
            System.out.println(" что оба их места на нижних полках.");
        }
    }
}
