package org.clientapp.commands;


import org.clientapp.modules.TCPclient;
import org.clientapp.blanks.TicketForm;
import org.clientapp.command_line.console.Console;
import org.commonapp.exceptions.APIException;
import org.commonapp.exceptions.IncorrectInputInScriptException;
import org.commonapp.exceptions.InvalidFormException;
import org.commonapp.exceptions.WrongAmountOfElementsException;
import org.commonapp.network_models.request.AddRequest;
import org.commonapp.network_models.response.AddResponse;

import java.io.IOException;

/**
 * Команда 'add'. Добавляет новый элемент в коллекцию.
 *
 
 */
public class Add extends Command {
    private final Console console;

    public Add(Console console) {
        super("add {element}");
        this.console = console;
    }

    /**
     * Выполняет команду
     *
     * @return Успешность выполнения команды.
     */
    @Override
    public boolean apply(String[] arguments) {

        try {
            if (!arguments[1].isEmpty())
                throw new WrongAmountOfElementsException();
            console.println("* Создание нового 'ticket':");
            var newTicket = (new TicketForm(console)).build();
            var response = (AddResponse) TCPclient.sendCommandAndReceiveResponse(new AddRequest(newTicket));
            if (response.getError() != null && !response.getError().isEmpty()) {
                throw new APIException(response.getError());
            }
            console.println("Новый ticket с id=" + response.newId + " успешно добавлен!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            console.printError("Неправильное количество аргументов!");
            console.println("спользование: '" + getName() + "'");
        } catch (InvalidFormException exception) {
            console.printError("Поля продукта не валидны! Продукт не создан!");
        } catch (IOException e) {
            console.printError("Ошибка взаимодействия с сервером");
        } catch (APIException e) {
            console.printError(e.getMessage());
        } catch (IncorrectInputInScriptException ignored) {
        } catch (ClassNotFoundException ignored) {
            console.printError("ClassNotFoundException");
        }
        return false;
    }
}
