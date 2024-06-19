package org.clientapp.commands;

import org.clientapp.blanks.LoginForm;
import org.clientapp.blanks.TicketForm;
import org.clientapp.command_line.console.Console;
import org.clientapp.modules.TCPclient;
import org.commonapp.exceptions.APIException;
import org.commonapp.exceptions.IncorrectInputInScriptException;
import org.commonapp.exceptions.InvalidFormException;
import org.commonapp.exceptions.WrongAmountOfElementsException;
import org.commonapp.network_models.request.AddRequest;
import org.commonapp.network_models.request.LoginRequest;
import org.commonapp.network_models.response.AddResponse;
import org.commonapp.network_models.response.LoginResponse;
import org.commonapp.utility.AuthModule;
import org.commonapp.utility.Hashing;

import java.io.IOException;
import java.util.Objects;

public class Login extends Command {
    private final Console console;

    public Login(Console console) {
        super("login ");
        this.console = console;
    }

    @Override
    public boolean apply(String[] arguments) {
        try {
            if (!arguments[1].isEmpty())
                throw new WrongAmountOfElementsException();
            console.println("* Authenticating:");
            var newUser = (new LoginForm(console)).build();
            System.out.println(newUser.login + " " + newUser.password);
            var response = (LoginResponse) TCPclient.sendCommandAndReceiveResponse(new LoginRequest(newUser));
            /*if (response.getError() != null && !response.getError().isEmpty()) {
                throw new APIException(response.getError());
            }*/
            AuthModule.setData(newUser.login, newUser.password);
            console.println(response.getError());
            if(Objects.equals(response.getError(), "error_password")) System.exit(1);
            return true;
        } catch (WrongAmountOfElementsException exception) {
            console.printError("Неправильное количество аргументов!");
            console.println("Использование: '" + getName() + "'");
        } catch (InvalidFormException exception) {
            console.printError("Поля продукта не валидны! Продукт не создан!");
        } catch (IOException e) {
            console.printError("Ошибка взаимодействия с сервером");
        } catch (IncorrectInputInScriptException ignored) {
        } catch (ClassNotFoundException ignored) {
            console.printError("ClassNotFoundException");
        }
        return false;
    }

}
