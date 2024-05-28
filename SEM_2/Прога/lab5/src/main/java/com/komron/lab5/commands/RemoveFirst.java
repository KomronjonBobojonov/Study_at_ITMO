package com.komron.lab5.commands;

import com.komron.lab5.exceptions.CollectionIsEmptyException;
import com.komron.lab5.exceptions.NotFoundException;
import com.komron.lab5.managers.CollectionManager;
import com.komron.lab5.utility.console.Console;
/**
 * Команда 'remove_first'. Удаляет первый элемент из коллекции.
 *
 *  
 */
public class RemoveFirst extends Command {

    private final Console console;
    private final CollectionManager collectionManager;

    public RemoveFirst(Console console, CollectionManager collectionManager) {
        super("remove_first", "удалить первый элемент из коллекции");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean apply(String[] arguments) {
        try {
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            var productToRemove = collectionManager.getFirst();
            if (productToRemove == null) throw new NotFoundException();

            collectionManager.removeFromCollection(productToRemove);
            console.println("First Movie успешно удален.");
            return true;
        } catch (CollectionIsEmptyException exception) {
            console.printError("Коллекция пуста!");
        } catch (NotFoundException e) {
            console.printError("Продукта с таким ID в коллекции нет!");
        }
        return false;
    }
}
