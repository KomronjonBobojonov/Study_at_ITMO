package org.commonapp.network_models.request;


import org.commonapp.model.Ticket;
import org.commonapp.model.User;
import org.commonapp.utility.Commands;

public class LoginRequest extends Request {
    public final User user;
    public LoginRequest(User user) {
        super(Commands.LOGIN);
        this.user = user;
    }
}
