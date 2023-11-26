package models.charackter;

import enums.Sizes;
import interfaces.Action;
import interfaces.Observer;
import models.actions.*;

public class Tigra extends AbsCharacter{
    public static String stcName = "Тигра";

    public Tigra() {
        super("Тигра");
    }
    public Observer happyjump = new HappyJumpAction(stcName);
    public Action comeback = new ComeBack();
    public Action asking = new Ask();
    public Action herego = new Wherego();

    public Action rushed = new Rushed(stcName);

    public Observer allfoot = new AllFoots();
}

