package org.clientapp.commands;


import org.clientapp.command_line.console.Console;
import org.commonapp.exceptions.APIException;
import org.commonapp.exceptions.WrongAmountOfElementsException;
import org.clientapp.modules.TCPclient;
import org.commonapp.network_models.request.RemoveByIdRequest;
import org.commonapp.network_models.response.RemoveByIdResponse;

import java.io.IOException;

/**
 * Команда 'remove_by_id'. Удаляет элемент из коллекции.
 
 */
public class RemoveById extends Command {
    private final Console console;

    public RemoveById(Console console) {
        super("remove_by_id <ID>");
        this.console = console;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public boolean apply(String[] arguments) {
        try {
            if (arguments[1].isEmpty()) throw new WrongAmountOfElementsException();
            var id = Integer.parseInt(arguments[1]);

            var response = (RemoveByIdResponse) TCPclient.sendCommandAndReceiveResponse(new RemoveByIdRequest(id));

            if (response.getError() != null && !response.getError().isEmpty()) {
                throw new APIException(response.getError());
            }

            console.println("Ticket успешно удален.");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            console.printError("Неправильное количество аргументов!");
            console.println("Использование: '" + getName() + "'");
        } catch (NumberFormatException exception) {
            console.printError("ID должен быть представлен числом!");
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
