package models.actions;

import enums.EveryTime;
import interfaces.Action;

public class ComeBack implements Action {

    private final EveryTime whatEVT;

    public ComeBack(EveryTime whatEVT) {
        this.whatEVT = whatEVT;
    }

    @Override
    public String perform() {
        return whatEVT.getName() + " возвращаясь";
    }
}
