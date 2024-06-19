package org.clientapp.commands;


import org.clientapp.command_line.console.Console;
import org.commonapp.exceptions.APIException;
import org.commonapp.exceptions.WrongAmountOfElementsException;
import org.clientapp.modules.TCPclient;
import org.commonapp.network_models.request.ClearRequest;
import org.commonapp.network_models.response.ClearResponse;

import java.io.IOException;

/**
 * Команда 'clear'. Очищает коллекцию.
 
 */
public class Clear extends Command {
    private final Console console;

    public Clear(Console console) {
        super("clear");
        this.console = console;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public boolean apply(String[] arguments) {
        try {
            if (!arguments[1].isEmpty()) throw new WrongAmountOfElementsException();

            var response = (ClearResponse) TCPclient.sendCommandAndReceiveResponse(new ClearRequest());
            if (response.getError() != null && !response.getError().isEmpty()) {
                throw new APIException(response.getError());
            }

            console.println("Коллекция очищена!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            console.printError("Неправильное количество аргументов!");
            console.println("Использование: '" + getName() + "'");
        } catch (IOException e) {
            console.printError("Ошибка взаимодействия с сервером");
        } catch (APIException e) {
            console.printError(e.getMessage());
        } catch (ClassNotFoundException e) {
            console.printError("ClassNotFoundException");
        }
        return false;
    }
}
