package com.komron.lab5.commands;

import com.komron.lab5.exceptions.CollectionIsEmptyException;
import com.komron.lab5.exceptions.IncorrectInputInScriptException;
import com.komron.lab5.exceptions.WrongAmountOfElementsException;
import com.komron.lab5.managers.CollectionManager;
import com.komron.lab5.utility.Interrogator;
import com.komron.lab5.utility.console.Console;

import java.util.NoSuchElementException;

import static com.komron.lab5.utility.Interrogator.fileMode;

public class CountByDiscount extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public CountByDiscount(Console console, CollectionManager collectionManager) {
        super("count_by_discount discount", "вывести количество элементов, значение поля discount которых равно заданному");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean apply(String[] arguments) {
        try {
            if (!arguments[1].isEmpty()) throw new WrongAmountOfElementsException();
            var discount = (askDiscount());
            var ans = collectionManager.getAmountOfEqualsElementsByDiscount(discount);
            System.out.println("Result: " + ans);
        } catch (WrongAmountOfElementsException e) {
            console.printError("Неправильное количество аргументов!");
            console.println("Использование: '" + getName() + "'");
        } catch (IncorrectInputInScriptException e) {
            console.printError("IncorrectInputInScriptException");
        } catch (CollectionIsEmptyException e) {
            console.printError("Collection is empty!");
        }
        return false;
    }

    private int askDiscount() throws IncorrectInputInScriptException {
        int discount;
        var fileMode = fileMode();
        while (true ) { // && Interrogator.getUserScanner().has
            try {
                console.println("Введите discount of ticket:");
                console.ps2();

                String partNumberStr = Interrogator.getUserScanner().nextLine().trim();
                if (fileMode) console.println(partNumberStr);
                if (partNumberStr.isEmpty()) {
                    throw new IllegalStateException("");
                }
                discount = Integer.parseInt(partNumberStr);
                break;
            } catch (NoSuchElementException exception) {
                console.printError("Номер части не распознан!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }

        return discount;
    }
}
