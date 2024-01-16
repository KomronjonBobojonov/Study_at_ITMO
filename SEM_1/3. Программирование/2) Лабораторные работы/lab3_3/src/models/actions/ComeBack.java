package models.actions;

import enums.Circumstsnces;
import enums.EveryTime;
import interfaces.Action;

public class ComeBack implements Action {

    private final EveryTime whatEVT;

    private final Circumstsnces whatdoing;

    public ComeBack(EveryTime whatEVT, Circumstsnces whatdoing) {
        this.whatEVT = whatEVT;
        this.whatdoing = whatdoing;
    }



    @Override
    public String perform() {
        return whatEVT.getName() + " " + whatdoing.getName();
    }
}
