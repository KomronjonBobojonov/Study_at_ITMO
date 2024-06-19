package org.clientapp.commands;


import org.clientapp.command_line.console.Console;
import org.clientapp.modules.TCPclient;
import org.clientapp.utility.Interrogator;
import org.commonapp.exceptions.APIException;
import org.commonapp.exceptions.IncorrectInputInScriptException;
import org.commonapp.exceptions.WrongAmountOfElementsException;
import org.commonapp.network_models.request.CountByDiscountRequest;
import org.commonapp.network_models.response.CountByDiscountResponse;

import java.io.IOException;
import java.util.NoSuchElementException;


public class CountByDiscount extends Command {
    private final Console console;

    public CountByDiscount(Console console) {
        super("count_by_discount discount");
        this.console = console;
    }

    @Override
    public boolean apply(String[] arguments) {
        try {
            if (!arguments[1].isEmpty()) throw new WrongAmountOfElementsException();
            var discount = (askDiscount());
            var response = (CountByDiscountResponse) TCPclient.sendCommandAndReceiveResponse(new CountByDiscountRequest(discount));
            if (response.getError() != null && !response.getError().isEmpty()) {
                throw new APIException(response.getError());
            }
            console.println("Result: " + response.getResult());
        } catch (WrongAmountOfElementsException e) {
            console.printError("Неправильное количество аргументов!");
            console.println("Использование: '" + getName() + "'");
        } catch (IOException e) {
            console.printError("Ошибка взаимодействия с сервером");
        } catch (APIException e) {
            console.printError(e.getMessage());
        } catch (IncorrectInputInScriptException e) {
            console.printError("IncorrectInputInScriptException");
        } catch (ClassNotFoundException e) {
            console.printError("ClassNotFoundException");
        }
        return false;
    }

    private int askDiscount() throws IncorrectInputInScriptException {
        int discount;
        var fileMode = Interrogator.fileMode();
        while (true) { // && Interrogator.getUserScanner().has
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
