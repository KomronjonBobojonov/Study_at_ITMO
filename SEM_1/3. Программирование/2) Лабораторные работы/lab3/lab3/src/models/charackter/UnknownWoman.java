package org.example.models.charackter;

import org.example.interfaces.Movable;
import org.example.interfaces.Observer;
import org.example.models.actions.FlashlightAction;
import org.example.models.actions.MoveToCorridorAction;
import org.example.models.actions.ObserveCorridorAction;

public class UnknownWoman extends AbsCharacter {

    public static String stcName = "Неизвестная женщина";

    public UnknownWoman() {
        super("Неизвестная женщина");
    }

    public FlashlightAction action = new FlashlightAction(stcName);
    public MoveToCorridorAction move = new MoveToCorridorAction();
    public Observer observeCorridorAction = new ObserveCorridorAction();
}
