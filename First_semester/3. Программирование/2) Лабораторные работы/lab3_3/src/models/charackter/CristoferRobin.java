package models.charackter;

import interfaces.Action;
import models.actions.AppearedHouse;
import models.actions.Was;

public class CristoferRobin extends AbsCharacter{
    public static String stcName = "Кристофер Робин";

    public CristoferRobin() {
        super("Кристофер Робин");
    }

    public Action was = new Was(stcName);
}
