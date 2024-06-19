package org.commonapp.network_models.response;

import org.commonapp.utility.Commands;

public class AddResponse extends Response {
    public final int newId;
    public AddResponse(int newId, String error) {
        super(Commands.ADD, error, String.valueOf(newId));
        this.newId = newId;
    }
}
