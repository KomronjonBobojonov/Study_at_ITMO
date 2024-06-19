package org.commonapp.network_models.request;

import org.commonapp.utility.Commands;
import org.commonapp.model.Ticket;

public class RemoveLowerRequest extends Request {
    public final Ticket ticket;

    public RemoveLowerRequest(Ticket ticket) {
        super(Commands.REMOVE_BY_ID);
        this.ticket = ticket;
    }
}

