package com.komron.lab5.commands;


import com.komron.lab5.exceptions.IncorrectInputInScriptException;
import com.komron.lab5.exceptions.InvalidFormException;
import com.komron.lab5.exceptions.WrongAmountOfElementsException;
import com.komron.lab5.managers.CollectionManager;
import com.komron.lab5.models.blanks.MovieForm;
import com.komron.lab5.utility.console.Console;

/**
 * Команда 'add'. Добавляет новый элемент в коллекцию.
 *
 *
 */
public class Add extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public Add(Console console, CollectionManager collectionManager) {
        super("add {element}", "добавить новый элемент в коллекцию");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполняет команду
     *
     * @return Успешность выполнения команды.
     */
    @Override
    public boolean apply(String[] arguments) {

        try {
            if (!arguments[1].isEmpty()) throw new WrongAmountOfElementsException();
            console.println("* Создание нового 'Movie':");
            collectionManager.addToCollection((new MovieForm(console, collectionManager)).build());
            console.println("'Movie' успешно добавлен!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            console.printError("Неправильное количество аргументов!");
            console.println("Использование: '" + getName() + "'");
        } catch (InvalidFormException exception) {
            console.printError("Поля 'Movie' не валидны! 'Movie' не создан!");
        } catch (IncorrectInputInScriptException ignored) {
        }
        return false;
    }
}
