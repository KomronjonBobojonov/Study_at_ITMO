package com.komron.lab5.models.blanks;


import com.komron.lab5.exceptions.IncorrectInputInScriptException;
import com.komron.lab5.exceptions.InvalidFormException;
import com.komron.lab5.models.Coordinates;
import com.komron.lab5.utility.Interrogator;
import com.komron.lab5.utility.console.Console;

import java.util.NoSuchElementException;
/**
 * Класс для ввода координат из консоли
 * @author Komron
 */
public class CoordinatesForm extends Form<Coordinates> {
    private final Console console;

    public CoordinatesForm(Console console) {
        this.console = console;
    }

    @Override
    public Coordinates build() throws IncorrectInputInScriptException, InvalidFormException {
        var coordinates = new Coordinates(askX(), askY());
        if (!coordinates.validate()) throw new InvalidFormException();
        return coordinates;
    }

    /**
     * Запрашивает у пользователя координату X.
     *
     * @return Координата X.
     * @throws IncorrectInputInScriptException Если запущен скрипт и возникает ошибка.
     */
    private Long askX() throws IncorrectInputInScriptException {
        var fileMode = Interrogator.fileMode();
        long x = 0;
        while (true) {
            try {
                console.println("Введите координату X:");
                console.ps2();
                var strX = Interrogator.getUserScanner().nextLine().trim();
                if (fileMode) console.println(strX);
                x = Long.parseLong(strX);
                break;
            } catch (NoSuchElementException exception) {
                console.printError("Координата X не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
                System.exit(0);
                break;
            } catch (NumberFormatException exception) {
                console.printError("Координата X должна быть представлена числом!");
//                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return x;
    }

    /**
     * Запрашивает у пользователя координату Y.
     *
     * @return Координата Y.
     * @throws IncorrectInputInScriptException Если запущен скрипт и возникает ошибка.
     */
    public Double askY() throws IncorrectInputInScriptException {
        var fileMode = Interrogator.fileMode();
        double y = 0;
        while (true) {
            try {
                console.println("Введите координату Y:");
                console.ps2();
                var strY = Interrogator.getUserScanner().nextLine().trim();
                if (fileMode) console.println(strY);
                y = Double.parseDouble(strY);
                break;
            } catch (NoSuchElementException exception) {
                console.printError("Координата Y не распознана!");
                if (fileMode) throw new IncorrectInputInScriptException();
                System.exit(0);
                break;
            } catch (NumberFormatException exception) {
                console.printError("Координата Y должна быть представлена числом!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (NullPointerException | IllegalStateException exception) {
                console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return y;
    }
}
