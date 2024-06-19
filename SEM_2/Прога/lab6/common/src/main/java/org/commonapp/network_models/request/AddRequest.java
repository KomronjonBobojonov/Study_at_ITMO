package org.commonapp.network_models.request;


import org.commonapp.utility.Commands;
import org.commonapp.model.Ticket;

public class AddRequest extends Request {
    public final Ticket ticket;
    public AddRequest(Ticket ticket) {
        super(Commands.ADD);
        this.ticket = ticket;
    }
}
