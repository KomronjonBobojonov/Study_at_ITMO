package org.commonapp.network_models.request;


import org.commonapp.utility.Commands;
import org.commonapp.model.Ticket;

public class UpdateRequest extends Request {
    public final int id;
    public final Ticket updatedTicket;

    public UpdateRequest(int id, Ticket updatedTicket) {
        super(Commands.UPDATE);
        this.id = id;
        this.updatedTicket = updatedTicket;
    }
}
