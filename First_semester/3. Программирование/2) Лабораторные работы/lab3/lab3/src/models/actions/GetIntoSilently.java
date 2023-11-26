package org.example.models.actions;

import org.example.interfaces.Action;

public class GetIntoSilently implements Action {
    @Override
    public String perform() {
        return "на цыпочках вошла туда";
    }
}
