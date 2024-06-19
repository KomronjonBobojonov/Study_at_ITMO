package org.clientapp.blanks;


import org.clientapp.command_line.console.Console;
import org.commonapp.exceptions.IncorrectInputInScriptException;
import org.commonapp.model.TicketType;
import org.clientapp.utility.Interrogator;

import java.util.NoSuchElementException;
/**
 * Класс для ввода тип билета из консоли
 
 */
public class TicketTypeForm extends Form<TicketType> {
    private final Console console;

    public TicketTypeForm(Console console) {
        this.console = console;
    }

    @Override
    public TicketType build() throws IncorrectInputInScriptException {

        var fileMode = Interrogator.fileMode();

        String strUnitOfMeasure;
        TicketType unitOfMeasure;
        while (true) {
            try {
                console.println("Список ticket types - " + TicketType.names());
                console.println("Введите ticket type (или null):");
                console.ps2();

                strUnitOfMeasure = Interrogator.getUserScanner().nextLine().trim();
                if (fileMode) console.println(strUnitOfMeasure);

                if (strUnitOfMeasure.isEmpty() || strUnitOfMeasure.equals("null")) return null;
                unitOfMeasure = TicketType.valueOf(strUnitOfMeasure.toUpperCase());
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
