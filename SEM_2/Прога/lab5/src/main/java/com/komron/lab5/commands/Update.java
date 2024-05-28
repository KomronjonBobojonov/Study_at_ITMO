package com.komron.lab5.commands;

import com.komron.lab5.exceptions.*;
import com.komron.lab5.managers.CollectionManager;
import com.komron.lab5.models.blanks.MovieForm;
import com.komron.lab5.utility.console.Console;
/**
 * Команда 'update <ID> {element}'. Обновляет значение элемента коллекции по ID.
 *
 *  
 */
public class Update extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public Update(Console console, CollectionManager collectionManager) {
        super("update <ID> {element}", "обновить значение элемента коллекции по ID");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean apply(String[] arguments) {
        try {
            if (arguments[1].isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            var id = Integer.parseInt(arguments[1]);
            var product = collectionManager.getById(id);
            if (product == null) throw new NotFoundException();

            console.println("* Введите данные обновленного продукта:");
            console.ps2();
            var newProduct = (new MovieForm(console, collectionManager)).build();
            product.update(newProduct);
            console.println("Movie успешно обновлен.");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            console.println("Использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            console.printError("Коллекция пуста!");
        } catch (NumberFormatException exception) {
            console.printError("ID должен быть представлен числом!");
        } catch (NotFoundException exception) {
            console.printError("Продукта с таким ID в коллекции нет!");
        } catch (IncorrectInputInScriptException e) {
            e.printStackTrace();
        } catch (InvalidFormException e) {
            console.printError("Поля продукта не валидны! Продукт не обновлен!");
        }
        return false;
    }
}
