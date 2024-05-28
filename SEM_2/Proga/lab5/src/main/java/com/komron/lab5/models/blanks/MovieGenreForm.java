package com.komron.lab5.models.blanks;

import com.komron.lab5.exceptions.IncorrectInputInScriptException;
import com.komron.lab5.models.MovieGenre;
import com.komron.lab5.utility.Interrogator;
import com.komron.lab5.utility.console.Console;

import java.util.NoSuchElementException;
/**
 * Класс для ввода тип билета из консоли
 *  
 */
public class MovieGenreForm extends Form<MovieGenre> {
    private final Console console;

    public MovieGenreForm(Console console) {
        this.console = console;
    }

    @Override
    public MovieGenre build() throws IncorrectInputInScriptException {

        var fileMode = Interrogator.fileMode();

        String strUnitOfMeasure;
        MovieGenre unitOfMeasure;
        while (true) {
            try {
                console.println("Список Movie Genre - " + MovieGenre.names());
                console.println("Введите Movie genre (или null):");
                console.ps2();

                strUnitOfMeasure = Interrogator.getUserScanner().nextLine().trim();
                if (fileMode) console.println(strUnitOfMeasure);

                if (strUnitOfMeasure.isEmpty() || strUnitOfMeasure.equals("null")) return null;
                unitOfMeasure = MovieGenre.valueOf(strUnitOfMeasure.toUpperCase());
                break;
            } catch (NoSuchElementException exception) {
                console.printError("Мера весов не распознано!");
                if (fileMode) throw new IncorrectInputInScriptException();
                System.exit(0);
            } catch (IllegalArgumentException exception) {
                console.printError("Меры весов нет в списке!");
                if (fileMode) throw new IncorrectInputInScriptException();
            } catch (IllegalStateException exception) {
                console.printError("Непредвиденная ошибка!");
//                System.exit(0);
            }
        }
        return unitOfMeasure;

    }

}
