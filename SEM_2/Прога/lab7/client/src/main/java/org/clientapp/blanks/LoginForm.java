package org.clientapp.blanks;

import org.clientapp.command_line.console.Console;
import org.clientapp.utility.Interrogator;
import org.commonapp.exceptions.IncorrectInputInScriptException;
import org.commonapp.exceptions.InvalidFormException;
import org.commonapp.exceptions.MustBeNotEmptyException;
import org.commonapp.model.User;

import java.util.NoSuchElementException;

public class LoginForm extends Form<User> {
    private final Console console;

    public LoginForm(Console console) {
        this.console = console;
    }

    @Override
    public User build() throws IncorrectInputInScriptException, InvalidFormException {
        var product = new User(
                askLogin(),
                askPassword()
        );
        if (!product.validate()) throw new InvalidFormException();
        return product;
    }

    private String askLogin() throws IncorrectInputInScriptException {
        String name = "";
        var scriptScanner = Interrogator.getUserScanner();
        while (true) {
            try {
                console.println("Введите username:");
                console.ps2();
                name = scriptScanner.nextLine().trim();
                if (name.isEmpty()) throw new MustBeNotEmptyException();
                break;
            } catch (NoSuchElementException exception) {
                console.printError("Название не распознано!");
                System.exit(0);
                break;
            } catch (MustBeNotEmptyException exception) {
                console.printError("Название не может быть пустым!");
            } catch (IllegalStateException exception) {
                console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return name;
    }

    private String askPassword() throws IncorrectInputInScriptException {
        String name = "";
        var scriptScanner = Interrogator.getUserScanner();
        while (true) {
            try {
                console.println("Введите password:");
                console.ps2();
                name = scriptScanner.nextLine().trim();
                if (name.isEmpty()) throw new MustBeNotEmptyException();
                break;
            } catch (NoSuchElementException exception) {
                console.printError("Название не распознано!");
                System.exit(0);
                break;
            } catch (MustBeNotEmptyException exception) {
                console.printError("Название не может быть пустым!");
            } catch (IllegalStateException exception) {
                console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        }
        return name;
    }


}
