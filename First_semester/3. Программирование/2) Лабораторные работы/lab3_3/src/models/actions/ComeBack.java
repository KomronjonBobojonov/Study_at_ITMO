package models.actions;

import interfaces.Action;

public class ComeBack implements Action {
    @Override
    public String perform() {
        return "поминутно возвращаясь";
    }
}
