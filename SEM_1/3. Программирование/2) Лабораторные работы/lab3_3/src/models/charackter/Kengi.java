package models.charackter;

import enums.Things;
import interfaces.Action;
import interfaces.Observer;
import models.actions.*;

public class Kengi extends AbsCharacter {
    public static String stcName = "Кенги";

    public Kengi() {
        super("Кенги");
    }

    public Action appearedhouse = new AppearedHouse(stcName, Things.House);

}
