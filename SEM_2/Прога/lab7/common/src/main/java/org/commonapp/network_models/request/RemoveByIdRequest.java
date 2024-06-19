package org.commonapp.network_models.request;


import org.commonapp.utility.Commands;

public class RemoveByIdRequest extends Request {
    public final int id;

    public RemoveByIdRequest(int id) {
        super(Commands.REMOVE_BY_ID);
        this.id = id;
    }
}
