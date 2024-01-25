package environment;

import enums.Locations;
import interfaces.Comeable;
import interfaces.Howable;


public class Vokzal extends Essence implements Comeable, Howable {

    public Vokzal(String name) {
        super(name);
    }
    @Override
    public void come(Locations name) {
        System.out.print("Прибыв" + " на вокзал ");
    }

    @Override
    public void comminghow(String how, Locations place) {
        //if (location == OUTSIDE){}
        System.out.print("задолго до отхода поезда");
    }
}
