package com.komron.lab5.models.blanks;

import com.komron.lab5.exceptions.IncorrectInputInScriptException;
import com.komron.lab5.exceptions.InvalidFormException;
import com.komron.lab5.models.Venue;
import com.komron.lab5.models.VenueType;
import com.komron.lab5.utility.Interrogator;
import com.komron.lab5.utility.console.Console;

import java.util.NoSuchElementException;
/**
 * Форма продукта.
 * @author Komron
 */
public class VenueForm extends Form<Venue> {
    private final Console console;

    public VenueForm(Console console) {
        this.console = console;
    }

    @Override
    public Venue build() throws IncorrectInputInScriptException, InvalidFormException {
        var venue = new Venue(askName(), askCapacity(), askVenueType());
        if (!venue.validate()) throw new InvalidFormException();
        return venue;
    }

    private String askName() throws IncorrectInputInScriptException {
        var fileMode = Interrogator.fileMode();
        String name;
        while (true) {
            try {
                console.println("Enter name of the venue:");
                console.ps2();
                var strName = Interrogator.getUserScanner().nextLine().trim();
                if (fileMode) console.println(strName);
                if (strName.isEmpty())
                    throw new IncorrectInputInScriptException();
                else name = strName;
                break;
            }catch (NoSuchElementException exception) {
                console.printError("Name of venue is not recognized!");
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
                console.println("Enter capacity of the venue:");
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
                console.printError("Capacity of venue is not recognized!");
//                if (fileMode) throw new IncorrectInputInScriptException();
                System.exit(0);
            }
            catch (IncorrectInputInScriptException e) {
                console.printError("Capacity is not good!");
            }
        }
        return capacity;
    }

    private VenueType askVenueType() throws IncorrectInputInScriptException {
        var fileMode = Interrogator.fileMode();
        VenueType venueType;
        while (true) {
            try {
                console.println("list of venue types - " + VenueType.names());
                console.println("Enter type of the venue (or null):");
                console.ps2();
                var value = Interrogator.getUserScanner().nextLine().trim();
                if (fileMode) console.println(value);
                if (value.equals("") || value.equals("null")) return null;
                venueType = VenueType.valueOf(value.toUpperCase());
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
        return venueType;
    }
}
