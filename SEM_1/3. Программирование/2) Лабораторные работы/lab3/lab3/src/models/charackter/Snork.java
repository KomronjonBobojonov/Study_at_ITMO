    package org.example.models.charackter;

import org.example.interfaces.Action;
import org.example.interfaces.Observer;
import org.example.models.actions.*;

public class Snork extends AbsCharacter {
    public static String stcName = "Фрекен Снорк";

    public Snork() {
        super("Фрекен Снорк");
    }

    public Observer notice = new NoticeLightAction(stcName);
    public Action comeCloser = new ComeCloserAction();

    public Action getIntoSilently = new GetIntoSilently();
    public Observer observeCharacter = new ObserveCharacterAction(Misa.stcName);
    public Action describeHead = new DescribeHeadCharacterAction();

}
