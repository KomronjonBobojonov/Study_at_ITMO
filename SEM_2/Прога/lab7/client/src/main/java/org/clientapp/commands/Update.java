package org.clientapp.commands;


import org.clientapp.blanks.TicketForm;
import org.clientapp.command_line.console.Console;
import org.commonapp.exceptions.APIException;
import org.commonapp.exceptions.IncorrectInputInScriptException;
import org.commonapp.exceptions.InvalidFormException;
import org.commonapp.exceptions.WrongAmountOfElementsException;
import org.clientapp.modules.TCPclient;
import org.commonapp.network_models.request.UpdateRequest;
import org.commonapp.network_models.response.UpdateResponse;

import java.io.IOException;

/**
 * Команда 'update'. Обновляет элемент коллекции.
 *  
 */
public class Update extends Command {
    private final Console console;
    private final TCPclient client;

    public Update(Console console, TCPclient client) {
        super("update ID {element}");
        this.console = console;
        this.client = client;
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
            console.println("* Введите данные обновленного продукта:");
            var updatedProduct = (new TicketForm(console)).build();
            var response = (UpdateResponse) TCPclient.sendCommandAndReceiveResponse(new UpdateRequest(id, updatedProduct));
            if (response.getError() != null && !response.getError().isEmpty()) {
                throw new APIException(response.getError());
            }
            console.println("Продукт успешно обновлен.");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            console.printError("Неправильное количество аргументов!");
            console.println("Использование: '" + getName() + "'");
        } catch (InvalidFormException exception) {
            console.printError("Поля продукта не валидны! Продукт не создан!");
        } catch (NumberFormatException exception) {
            console.printError("ID должен быть представлен числом!");
        } catch (IOException e) {
            console.printError("Ошибка взаимодействия с сервером");
        } catch (APIException e) {
            console.printError(e.getMessage());
        } catch (IncorrectInputInScriptException ignored) {
        } catch (ClassNotFoundException e) {
            console.printError("ClassNotFoundException " + e.getMessage());
        }
        return false;
    }
}
