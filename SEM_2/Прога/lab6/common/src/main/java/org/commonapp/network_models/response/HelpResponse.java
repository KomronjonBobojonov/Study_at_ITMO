package org.commonapp.network_models.response;


import org.commonapp.utility.Commands;

public class HelpResponse extends Response {
    public final String helpMessage;

    public HelpResponse(String helpMessage, String error) {
        super(Commands.HELP, error, "");
        this.helpMessage = helpMessage;
    }
}
