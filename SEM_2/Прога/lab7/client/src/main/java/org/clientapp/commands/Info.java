package org.clientapp.commands;


import org.clientapp.command_line.console.Console;
import org.clientapp.modules.TCPclient;
import org.commonapp.network_models.request.InfoRequest;
import org.commonapp.network_models.response.InfoResponse;

import java.io.IOException;

/**
 * Команда 'info'. Выводит информацию о коллекции.
 *  
 */
public class Info extends Command {
    private final Console console;

    public Info(Console console) {
        super("info");
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
            var response = (InfoResponse) TCPclient.sendCommandAndReceiveResponse(new InfoRequest());
            console.print(response.infoMessage);
            return true;
        } catch (IOException e) {
            console.printError("Ошибка взаимодействия с сервером");
        } catch (ClassNotFoundException e) {
            console.printError("Class Not Found!");
        }
        return false;
    }
}
