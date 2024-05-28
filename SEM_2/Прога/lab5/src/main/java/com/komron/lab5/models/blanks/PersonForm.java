package com.komron.lab5.models.blanks;

import com.komron.lab5.exceptions.IncorrectInputInScriptException;
import com.komron.lab5.exceptions.InvalidFormException;
import com.komron.lab5.models.Person;
import com.komron.lab5.models.PersonType;
import com.komron.lab5.utility.Interrogator;
import com.komron.lab5.utility.console.Console;

import java.util.NoSuchElementException;
/**
 * Форма продукта.
 *  
 */
public class PersonForm extends Form<Person> {
    private final Console console;

    public PersonForm(Console console) {
        this.console = console;
    }

    @Override
    public Person build() throws IncorrectInputInScriptException, InvalidFormException {
        var Person = new Person(askName(), askCapacity(), askPersonType());
        if (!Person.validate()) throw new InvalidFormException();
        return Person;
    }

    private String askName() throws IncorrectInputInScriptException {
        var fileMode = Interrogator.fileMode();
        String name;
        while (true) {
            try {
                console.println("Enter name of the Person:");
                console.ps2();
                var strName = Interrogator.getUserScanner().nextLine().trim();
                if (fileMode) console.println(strName);
                if (strName.isEmpty())
                    throw new IncorrectInputInScriptException();
                else name = strName;
                break;
            }catch (NoSuchElementException exception) {
                console.printError("Name of Person is not recognized!");
                if (fileMode) throw new IncorrectInputInScriptException();
                System.exit(0);
            }  catch (IncorrectInputInScriptException exception) {
                console.printError("Name is empty!");
//                System.exit(0);
            }
        }
        return name;
    }

    private long askCapacity() {
        var fileMode = Interrogator.fileMode();
        long capacity;
        while (true) {
            try {
                console.println("Enter capacity of the Person:");
                console.ps2();
                var value = Interrogator.getUserScanner().nextLine().trim();
                if (fileMode) console.println(value);
                long k = Long.parseLong(value);
                if (k > 0) {
                    capacity = k;
                } else throw new IncorrectInputInScriptException();
                break;
            }
            catch (NoSuchElementException exception) {
                console.printError("Capacity of Person is not recognized!");
//                if (fileMode) throw new IncorrectInputInScriptException();
                System.exit(0);
            }
            catch (IncorrectInputInScriptException e) {
                console.printError("Capacity is not good!");
            }
        }
        return capacity;
    }

    private PersonType askPersonType() throws IncorrectInputInScriptException {
        var fileMode = Interrogator.fileMode();
        PersonType personType;
        while (true) {
            try {
                console.println("list of Person eyeColor - " + PersonType.names());
                console.println("Enter eyeColor of the Person (or null):");
                console.ps2();
                var value = Interrogator.getUserScanner().nextLine().trim();
                if (fileMode) console.println(value);
                if (value.equals("") || value.equals("null")) return null;
                personType = PersonType.valueOf(value.toUpperCase());
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
                System.exit(0);
            }
        }
        return personType;
    }
}
