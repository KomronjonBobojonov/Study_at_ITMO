package org.serverapp.commands;


import org.serverapp.managers.CommandManager;
import org.commonapp.network_models.request.Request;
import org.commonapp.network_models.response.HelpResponse;
import org.commonapp.network_models.response.Response;

/**
 * Команда 'help'. Выводит справку по доступным командам
 *  
 */
public class Help extends Command {
    private final CommandManager commandManager;

    public Help(CommandManager commandManager) {
        super("help", "вывести справку по доступным командам");
        this.commandManager = commandManager;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public Response apply(Request request) {
        var helpMessage = new StringBuilder();

        commandManager.getCommands().values().forEach(command -> {
//      helpMessage.append(" %-35s%-1s%n".formatted(command.getName(), command.getDescription()));
            helpMessage.append(command.getName()).append(" ").append(command.getDescription()).append("\r\n");
        });

        return new HelpResponse(helpMessage.toString(), null);
    }
}
