package org.commonapp.network_models.response;

import org.commonapp.utility.Commands;

public class RemoveFirstResponse extends Response {
    public RemoveFirstResponse(String error) {
        super(Commands.REMOVE_FIRST, error, "");
    }
}
