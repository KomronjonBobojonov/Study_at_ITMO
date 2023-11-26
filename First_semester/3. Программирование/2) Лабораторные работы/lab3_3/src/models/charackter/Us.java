package models.charackter;

import enums.Sizes;
import interfaces.Action;
import models.actions.Seems;

public class Us extends AbsCharacter{
    public static String stcName = "нам!";

    public Us() {
        super("нам!");
    }

    public Action action = new Seems(stcName, Sizes.SMALL);
}
