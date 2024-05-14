package com.komron.lab5.commands;

import com.komron.lab5.exceptions.CollectionIsEmptyException;
import com.komron.lab5.exceptions.IncorrectInputInScriptException;
import com.komron.lab5.exceptions.InvalidFormException;
import com.komron.lab5.exceptions.WrongAmountOfElementsException;
import com.komron.lab5.managers.CollectionManager;
import com.komron.lab5.models.blanks.TicketForm;
import com.komron.lab5.utility.console.Console;

/**
 * Команда 'remove_lower {element}'. Удаляет все элементы, меньшие, чем заданный из коллекции.
 *
 * @author Komron
 */
public class RemoveLower extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public RemoveLower(Console console, CollectionManager collectionManager) {
        super("remove_lower {element}", "удалить из коллекции все элементы, меньшие, чем заданный");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean apply(String[] arguments) {
        try {
            if (!arguments[1].isEmpty()) throw new WrongAmountOfElementsException();
            console.println("* Создание нового ticket (remove_lower):");
            var product = (new TicketForm(console, collectionManager)).build();
            collectionManager.removeLowerPricedTickets(product);
            return true;
        } catch (WrongAmountOfElementsException e) {
            console.printError("Неправильное количество аргументов!");
            console.println("Использование: '" + getName() + "'");
        } catch (IncorrectInputInScriptException e) {
            console.printError("IncorrectInputInScriptException");
        } catch (InvalidFormException e) {
            console.printError("Поля ticket не валидны! Продукт не создан!");
        } catch (CollectionIsEmptyException e) {
            console.printError("Collection is empty!");
        }
        return false;
    }
}
