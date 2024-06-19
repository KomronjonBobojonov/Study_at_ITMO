package org.clientapp.command_line;


import org.clientapp.command_line.console.Console;
import org.clientapp.commands.*;
import org.commonapp.utility.AuthModule;
import org.clientapp.modules.TCPclient;
import org.clientapp.utility.Interrogator;
import org.commonapp.utility.Commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Runner {
    public enum RunnerStatus {
        OK,
        ERROR,
        EXIT,
    }

    private final Console console;

    private final Map<String, Command> commands;

    private final List<String> scriptStack = new ArrayList<>();

    public Runner(TCPclient client, Console console) {
        Interrogator.setUserScanner(new Scanner(System.in));
        this.console = console;

        this.commands = new HashMap<>() {{
            put(Commands.HELP, new Help(console));
            put(Commands.INFO, new Info(console));
            put(Commands.SHOW, new Show(console));
            put(Commands.ADD, new Add(console));
            put(Commands.UPDATE, new Update(console, client));
            put(Commands.REMOVE_BY_ID, new RemoveById(console));
            put(Commands.CLEAR, new Clear(console));
            put(Commands.HEAD, new Head(console));
            put(Commands.EXECUTE_SCRIPT, new ExecuteScript(console));
            put(Commands.EXIT, new Exit(console));
            put(Commands.REMOVE_FIRST, new RemoveFirst(console));
            put(Commands.REMOVE_LOWER, new RemoveLower(console));
            put(Commands.COUNT_BY_DISCOUNT, new CountByDiscount(console));
            put(Commands.FILTER_GREATER_THAN_DISCOUNT, new FilterGreaterThanDiscount(console));
            put(Commands.PRINT_ASCENDING, new PrintAscending(console));
            put(Commands.LOGIN, new Login(console));
        }};
    }

    /**
     * Интерактивный режим
     */
    public void interactiveMode() {
        var userScanner = Interrogator.getUserScanner();
        try {
            RunnerStatus commandStatus;
            String[] userCommand = {"", ""};
            do {
                if (!AuthModule.isAuthenticated) {
                    userCommand = new String[]{"login", ""};
                } else {
                    userCommand = (userScanner.nextLine().trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                    System.out.println("Usercommand: " + userCommand[0]);

                }
                commandStatus = launchCommand(userCommand);
            } while (commandStatus != RunnerStatus.EXIT && userScanner.hasNext());
            Interrogator.setUserScanner(new Scanner(System.in));
        } catch (NoSuchElementException exception) {
            console.printError("Пользовательский ввод не обнаружен!");
        } catch (IllegalStateException exception) {
            console.printError("Непредвиденная ошибка!");
        }
    }


    /**
     * Режим для запуска скрипта.
     *
     * @param argument Аргумент скрипта
     * @return Код завершения.
     */
    public RunnerStatus scriptMode(String argument) {
        String[] userCommand = {"", ""};
        RunnerStatus commandStatus = RunnerStatus.OK;
        scriptStack.add(argument);
        try {
            if (!new File(argument).exists()) {
                argument = "../" + argument;
            }
            List<String> lines = Files.readAllLines(Paths.get(argument));
            StringBuilder coms = new StringBuilder();
            for (String line : lines) {
                coms.append(line);
                coms.append("\n");
                // System.out.println(line);
            }
            Interrogator.setUserScanner(new Scanner(coms.toString()));
            var scriptScanner = Interrogator.getUserScanner();
            try {
                if (!scriptScanner.hasNext()) throw new NoSuchElementException();
                Interrogator.setFileMode();

                while (scriptScanner.hasNext()) {
                    userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                    while (scriptScanner.hasNextLine() && userCommand[0].isEmpty()) {
                        userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                        userCommand[1] = userCommand[1].trim();
                    }
                    console.println(console.getPS1() + String.join(" ", userCommand));
                    var hasRecursion = false;
                    if (userCommand[0].equals("execute_script")) {
                        for (String script : scriptStack) {
                            if (userCommand[1].equals(script)) {
                                hasRecursion = true;
                                console.println("Recursion detected! Skipping the command.");
                            }
                        }
                    }
                    if (!hasRecursion) {
                        commandStatus = launchCommand(userCommand);
                    } else {
                        commandStatus = RunnerStatus.OK;
                    }

                }

                if (commandStatus == RunnerStatus.ERROR && !(userCommand[0].equals("execute_script") && !userCommand[1].isEmpty())) {
                    console.println("Проверьте скрипт на корректность введенных данных!");
                }

                return commandStatus;

            } /*catch (FileNotFoundException exception) {
                console.printError("Файл со скриптом не найден!");
            } */ catch (NoSuchElementException exception) {
                console.printError("Файл со скриптом пуст!");
            } /*catch (ScriptRecursionException exception) {
            console.printError("Скрипты не могут вызываться рекурсивно!");
        } */ catch (IllegalStateException exception) {
                console.printError("Непредвиденная ошибка!");
                System.exit(0);
            } catch (SecurityException e) {
                console.printError("Security ошибка!");
            } finally {
                scriptStack.remove(scriptStack.size() - 1);
            }
        } catch (SecurityException e) {
            console.printError("Security ошибка!");
        } catch (IOException e) {
            console.printError(e.getMessage());
        }
        return RunnerStatus.ERROR;
    }

    /**
     * Launchs the command.
     *
     * @param userCommand Команда для запуска
     * @return Код завершения.
     */
    private RunnerStatus launchCommand(String[] userCommand) {
        if (userCommand[0].isEmpty()) return RunnerStatus.OK;
        var command = commands.get(userCommand[0]);

        if (command == null) {
            console.printError("Команда '" + userCommand[0] + "' не найдена. Наберите 'help'");
            return RunnerStatus.ERROR;
        }

        switch (userCommand[0]) {
            case "exit" -> {
                if (!commands.get("exit").apply(userCommand)) return RunnerStatus.ERROR;
                else {
                    return RunnerStatus.EXIT;
                }
            }
            case "execute_script" -> {
                if (!commands.get("execute_script").apply(userCommand)) return RunnerStatus.ERROR;
                else {
                    return scriptMode(userCommand[1]);
                }
            }
            default -> {
                if (!command.apply(userCommand)) {
                    return RunnerStatus.ERROR;
                }
            }
        }
        return RunnerStatus.OK;
    }
}
