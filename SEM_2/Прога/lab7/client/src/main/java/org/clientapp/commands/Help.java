package org.clientapp.commands;


import org.clientapp.command_line.console.Console;
import org.clientapp.modules.TCPclient;
import org.commonapp.network_models.request.HelpRequest;
import org.commonapp.network_models.response.HelpResponse;

import java.io.IOException;

/**
 * Команда 'help'. Выводит справку по доступным командам
 *  
 */
public class Help extends Command {
    private final Console console;

    public Help(Console console) {
        super("help");
        this.console = console;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public boolean apply(String[] arguments) {
        if (!arguments[1].isEmpty()) {
            console.printError("Неправильное количество аргументов!");
            console.println("Использование: '" + getName() + "'");
            return false;
        }

        try {
            var response = (HelpResponse) TCPclient.sendCommandAndReceiveResponse(new HelpRequest());
            console.print(response.helpMessage);
            return true;
        } catch (IOException e) {
            console.printError("Ошибка взаимодействия с сервером");
        } catch (ClassNotFoundException e) {
            console.printError("Class Not Found!");
        }
        return false;
    }
}
