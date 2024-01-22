import enums.Gengers;
import enums.Locations;
import enums.ShelfInVagons;
import environment.Person;
import environment.Vagon;
import environment.Vokzal;
import models.charackter.*;

import java.util.ArrayList;
import java.util.List;


/**
 Прибыв на вокзал задолго до отхода поезда, они вошли в вагон.
 Проводник проверил их билеты и сказал, что оба их места на верхних полках.
 Забравшись на верхние полки.
 Незнайка и Козлик с удобством растянулись на них и принялись наблюдать украдкой за прибывающими пассажирами.
 Вагон тем временем понемножку наполнялся.
 Внизу, как раз под полкой, на которой лежал Незнайка, расположился какой-то толстенький коротышка.
 Сунув чемодан под сиденье, он вытащил из кармана целый ворох газет и принялся читать их.
 */


public class Main {
    public static void main(String[] args) {
        Vagon vagon = new Vagon();
        Neznaika neznaika = new Neznaika("Незнайка", Gengers.FEMALE, Locations.OUTSIDE, ShelfInVagons.TOP);
        Korotishka korotishka = new Korotishka("коротышка", Gengers.MALE, Locations.OUTSIDE, ShelfInVagons.BOTTOM);
        Kozlik kozlik = new Kozlik("Козлик", Gengers.MALE, Locations.OUTSIDE, ShelfInVagons.TOP);
        Vokzal vokzal = new Vokzal("вокзал");
        Provodnik provodnik = new Provodnik("Проводник", Gengers.MALE, Locations.VAGON, ShelfInVagons.BOTTOM);
//        Person pespo = new Korotishka("Незнайка", Gengers.FEMALE, Locations.OUTSIDE, ShelfInVagons.TOP);


        vokzal.come(kozlik.location);
        vokzal.comminghow(kozlik.getName(), kozlik.location);

        List<Person> listOfPassengers = new ArrayList<Person>();
        listOfPassengers.add(neznaika);
        listOfPassengers.add(kozlik);
        vagon.enter(listOfPassengers);
        Group group = new Group(listOfPassengers);

        provodnik.check(kozlik.getName());
        provodnik.said(kozlik);
        vagon.climbingup(listOfPassengers);


        group.streched(listOfPassengers);
        listOfPassengers.add(korotishka);
        listOfPassengers.add(provodnik);
        group.lookafterable(listOfPassengers);

        listOfPassengers.add(korotishka);
        vagon.vagonfillingup(listOfPassengers);

    }
}