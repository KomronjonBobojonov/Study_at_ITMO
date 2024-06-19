package org.clientapp.commands;

import org.clientapp.command_line.console.Console;
import org.clientapp.modules.TCPclient;
import org.commonapp.exceptions.APIException;
import org.commonapp.exceptions.WrongAmountOfElementsException;
import org.commonapp.network_models.request.ShowRequest;
import org.commonapp.network_models.response.ShowResponse;

import java.io.IOException;

/**
 * Команда 'show'. Выводит все элементы коллекции.
 *  
 */
public class Show extends Command {
    private final Console console;

    public Show(Console console) {
        super("show");
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

            var response = (ShowResponse) TCPclient.sendCommandAndReceiveResponse(new ShowRequest());
            if (response.getError() != null && !response.getError().isEmpty()) {
                throw new APIException(response.getError());
            }
            if (response.productsList.isEmpty()) {
                console.println("Коллекция пуста!");
                return true;
            }
            console.println((response.productsList).stream().toList() + "\n");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            console.printError("Неправильное количество аргументов!");
            console.println("Использование: '" + getName() + "'");
        } catch (IOException e) {
            e.printStackTrace();
            console.printError("Ошибка взаимодействия с сервером " + e.getLocalizedMessage());
        } catch (APIException e) {
            console.printError(e.getMessage());
        } catch (ClassNotFoundException e) {
            console.printError("Class Not Found!");
        }
        return false;
    }
}
