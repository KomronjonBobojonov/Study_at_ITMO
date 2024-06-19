package org.serverapp.handlers;


import org.serverapp.managers.CommandManager;
import org.commonapp.network_models.request.Request;
import org.commonapp.network_models.response.NoSuchCommandResponse;
import org.commonapp.network_models.response.Response;

public class CommandHandler {
    private final CommandManager manager;

    public CommandHandler(CommandManager manager) {
        this.manager = manager;
    }

    public Response handle(Request request) {
        var command = manager.getCommands().get(request.getName());
        if (command == null) return new NoSuchCommandResponse(request.getName());
        return command.apply(request);
    }
}
