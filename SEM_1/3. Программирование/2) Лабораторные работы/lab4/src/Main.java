import enums.Gengers;
import enums.Locations;
import enums.ShelfInVagons;
import environment.Person;
import environment.Room;
import environment.Vagon;
import environment.Vokzal;
import exception.InvalidatedException;
import exception.PassengersNotFoundException;
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
 В то время как Незнайка и Козлик удобно устроились на верхних полках, наблюдая за пассажирами,
 толстенький коротышка внизу, увлечённо читая газеты, не заметил, как рядом с ним уселась старушка с большой корзиной.
 В корзине что-то шевелилось, и вскоре из неё выглянула голова любопытной кошки, которая тут же привлекла внимание Козлика.
 */


public class Main {
    public static void main(String[] args) {
        Vagon vagon = new Vagon();
        Neznaika neznaika = new Neznaika("Незнайка", Gengers.FEMALE, Locations.OUTSIDE, ShelfInVagons.TOP);
        Korotishka korotishka = new Korotishka("коротышка", Gengers.MALE, Locations.OUTSIDE, ShelfInVagons.BOTTOM);
        Kozlik kozlik = new Kozlik("Козлик", Gengers.MALE, Locations.OUTSIDE, ShelfInVagons.TOP);
        Vokzal vokzal = new Vokzal("вокзал");
        Room room = new Room("Комната");
        Provodnik provodnik = new Provodnik("Проводник", Gengers.MALE, Locations.VAGON, ShelfInVagons.BOTTOM);
        Starushka starushka = new Starushka("старушка", Gengers.FEMALE, Locations.VAGON, ShelfInVagons.BOTTOM);

        vokzal.come(kozlik.location);
        vokzal.comminghow(kozlik.getName(), kozlik.location);

        List<Person> listOfPassengers = new ArrayList<Person>();
        listOfPassengers.add(neznaika);
        listOfPassengers.add(kozlik);
        vagon.enter(listOfPassengers);
        Group group = new Group(listOfPassengers);
        try {
            provodnik.check(kozlik.getName());
        }catch (InvalidatedException sww){
            System.out.print(provodnik.getName() + "не впустил их, так как у них отсутствует билет");
        }
        provodnik.said(kozlik);
        vagon.climbingup(listOfPassengers);


        group.streched(listOfPassengers);
        listOfPassengers.add(korotishka);
        listOfPassengers.add(provodnik);
        try {
            group.lookafterable(listOfPassengers);
        }catch (PassengersNotFoundException Abc){
            System.out.println("Бро пассажиров нет");
        }

        listOfPassengers.add(korotishka);
        vagon.vagonfillingup(listOfPassengers);

        room.lay(neznaika);

        neznaika.read(listOfPassengers);
        korotishka.set(starushka);

        starushka.move("В корзине что-то шевелилось, ");
        kozlik.attract(kozlik);

    }
}