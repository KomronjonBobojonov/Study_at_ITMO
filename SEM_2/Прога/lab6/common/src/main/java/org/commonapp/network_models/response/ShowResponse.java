package org.commonapp.network_models.response;


import org.commonapp.utility.Commands;
import org.commonapp.model.Ticket;

import java.util.List;

public class ShowResponse extends Response {
    public final List<Ticket> productsList;

    public ShowResponse(String error, List<Ticket> productsList) {
        super(Commands.SHOW, error, "");
        this.productsList = productsList;
    }
}
