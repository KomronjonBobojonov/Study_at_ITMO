package org.example.models.charackter;

import org.example.interfaces.Action;
import org.example.models.actions.SilenceAction;

public class Misa extends AbsCharacter {
    public static String stcName = "Кристофер Робин";

    public Misa() {
        super("Кристофер Робин");
    }

    public Action respond = new SilenceAction(stcName);

}
