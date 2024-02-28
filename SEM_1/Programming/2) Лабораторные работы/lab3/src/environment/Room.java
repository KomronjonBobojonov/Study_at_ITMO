package environment;

import enums.Locations;
import interfaces.Layable;

public class Room extends Essence implements Layable {
    public Room(String name) {
        super(name);
    }

    @Override
    public void lay(Person neznaika) {
        //Внизу, как раз под полкой, на которой лежал Незнайка, расположился какой-то толстенький коротышка.
        neznaika.location = Locations.INROOM;
        if(neznaika.location == Locations.INROOM){
            System.out.print("Внизу, как раз под полкой, на которой лежал " + neznaika.getName());
        }else{
            System.out.print(neznaika.getName() + " сидел и считал голубей.");
        }
    }
}
