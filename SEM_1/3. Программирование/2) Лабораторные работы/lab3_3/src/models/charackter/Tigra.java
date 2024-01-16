package models.charackter;

import enums.*;
import interfaces.Action;
import interfaces.Observer;
import models.actions.*;

public class Tigra extends AbsCharacter {
    public static String stcName = "Тигра";

    public Tigra() {
        super("Тигра");
    }
    public Observer happyjump = new HappyJumpAction(stcName, MeanTime.TimeT);
    public Action comeback = new ComeBack(EveryTime.Time, Circumstsnces.Comeing);
    public Action asking = new Ask();
    public Action herego = new Wherego();
    public String rushed(Circumstsnces how, Pronouns pronoun) {
        return stcName + " brodsilysa " + how.getName() + " k " + pronoun.getName();
    }
}


