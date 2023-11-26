    package org.example.models.actions;

import org.example.interfaces.Action;

public class ComeCloserAction implements Action {
    @Override
    public String perform() {
        return "пробивающуюся сквозь полуоткрытую дверь";
    }
}
