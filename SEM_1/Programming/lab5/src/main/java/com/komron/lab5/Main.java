package com.komron.lab5;

import com.komron.lab5.commands.*;
import com.komron.lab5.managers.CollectionManager;
import com.komron.lab5.managers.CommandManager;
import com.komron.lab5.managers.DumpManager;
import com.komron.lab5.models.Ticket;
import com.komron.lab5.utility.Interrogator;
import com.komron.lab5.utility.Runner;
import com.komron.lab5.utility.console.StandardConsole;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Interrogator.setUserScanner(new Scanner(System.in));
        var console = new StandardConsole();
        if (args.length == 0) {
            console.println("Введите имя загружаемого файла как аргумент командной строки");
            System.exit(1);
        }
        var dumpManager = new DumpManager(args[0], console);
        var collectionManager = new CollectionManager(dumpManager);
        Ticket.updateNextId(collectionManager);
        collectionManager.validateAll(console);

        var commandManager = new CommandManager() {{
            add("help", new Help(console, this));
            add("info", new Info(console, collectionManager));
            add("show", new Show(console, collectionManager));
            add("add", new Add(console, collectionManager));
            add("update", new Update(console, collectionManager));
            add("remove_by_id", new RemoveById(console, collectionManager));
            add("clear", new Clear(console, collectionManager));
            add("save", new Save(console, collectionManager));
            add("execute_script", new ExecuteScript(console));
            add("exit", new Exit(console));
            add("head", new Head(console, collectionManager));
            add("remove_first", new RemoveFirst(console, collectionManager));
            add("remove_lower", new RemoveLower(console, collectionManager));
            add("count_by_discount", new CountByDiscount(console, collectionManager));
            add("filter_greater_than_discount", new FilterGreaterThanDiscount(console, collectionManager));

            add("print_ascending", new PrintAscending(console, collectionManager));
        }};
        new Runner(console, commandManager).interactiveMode();

    }
}