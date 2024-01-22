package environment;

import enums.Gengers;
import enums.Locations;
import enums.ShelfInVagons;
import interfaces.Climbable;
import interfaces.Enterable;
import interfaces.Fillable;

import java.util.Collections;
    import java.util.List;

    public class Vagon extends Essence implements Enterable, Climbable, Fillable {
        List<Person> passengers;
        public Vagon() {
        super("вагон");
        passengers = Collections.emptyList();
        }
        @Override
        public void enter(List<Person> people) {
            passengers = people;
            String passengerName = "";
            if(passengers.size() > 1){
                passengerName = "они";
            }else if(passengers.size() == 1){
                if (passengers.get(0).gender == Gengers.MALE)
                    passengerName = "он";
                else{
                    passengerName = "она";
                }
            }
            System.out.println(", "+passengerName + " вошли в " + this.name);
            passengers.get(0).location = Locations.VAGON;
            passengers.get(1).location = Locations.VAGON;
        }

        @Override
        public void climbingup(List<Person> people) {
            passengers = people;
            if(passengers.get(0).shelfInVagon == ShelfInVagons.TOP && passengers.get(1).shelfInVagon == ShelfInVagons.TOP){
                System.out.println("Забравшись на верхние полки.");
            }else if(passengers.get(0).shelfInVagon == ShelfInVagons.BOTTOM && passengers.get(1).shelfInVagon == ShelfInVagons.BOTTOM){
                System.out.println("Забравшись на нижние полки.");
            }else {
                System.out.println("Забравшись каждый по своим полкам.");
            }
        }

        @Override
        public void vagonfillingup(List<Person> people) {
            passengers = people;
            if(passengers.size() >= 3){
                System.out.println(this.name + " тем временем понемножку наполнялся.");
            }else {
                System.out.println("В " + this.name + "е тем временем было только два пассажира.");
            }
        }
    }

