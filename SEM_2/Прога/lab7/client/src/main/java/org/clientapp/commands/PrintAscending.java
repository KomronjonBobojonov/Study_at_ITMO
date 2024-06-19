package org.clientapp.commands;


import org.clientapp.command_line.console.Console;
import org.clientapp.modules.TCPclient;
import org.commonapp.network_models.request.PrintAscendingRequest;
import org.commonapp.network_models.response.PrintAscendingResponse;

import java.io.IOException;

/**
 * Команда 'print_ascending'. Выводит элементы коллекции в порядке возрастания.
 *
 *  
 */
public class PrintAscending extends Command {
    private final Console console;

    public PrintAscending(Console console) {
        super("print_ascending");
        this.console = console;
    }


    @Override
    public boolean apply(String[] arguments) {
        try {
            var response = (PrintAscendingResponse) TCPclient.sendCommandAndReceiveResponse(new PrintAscendingRequest());
            console.print(response.getResult());
            return true;
        } catch (IOException e) {
            console.printError("Ошибка взаимодействия с сервером");
        } catch (ClassNotFoundException e) {
            console.printError("ClassNotFoundException");
        }
        return false;
    }
}
