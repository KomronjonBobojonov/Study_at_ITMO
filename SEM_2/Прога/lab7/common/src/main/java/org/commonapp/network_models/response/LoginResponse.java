package org.commonapp.network_models.response;

import org.commonapp.utility.Commands;

public class LoginResponse extends Response {
    public final boolean isSuccess;

    public LoginResponse(boolean isSuccess, String error) {
        super(Commands.LOGIN, error, error);
        this.isSuccess = isSuccess;
    }
}
