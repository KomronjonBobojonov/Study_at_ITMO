package org.commonapp.network_models.request;

import org.commonapp.utility.Commands;

public class RemoveFirstRequest extends Request {

    public RemoveFirstRequest() {
        super(Commands.REMOVE_FIRST);
    }
}
