package org.commonapp.network_models.response;

import org.commonapp.utility.Commands;

public class RemoveLowerResponse extends Response {
    public RemoveLowerResponse(String error) {
        super(Commands.REMOVE_LOWER, error, "");
    }
}
