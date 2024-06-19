package org.serverapp;
import org.serverapp.commands.*;
import org.serverapp.handlers.CommandHandler;
import org.serverapp.managers.CommandManager;
import org.serverapp.modules.TCPserver;
import org.serverapp.managers.DumpManager;
import org.serverapp.managers.TicketRepository;
import org.commonapp.utility.Commands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
public class Main {
    public static final int PORT = 21279;
    public static Logger logger = LoggerFactory.getLogger(Main.class);
    public static String dirPath;

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Введите имя загружаемого файла как аргумент командной строки");
            System.exit(1);
        }
        dirPath = args[0];
        String filePath = args[0] + "/data1.txt";
        var dumpManager = new DumpManager(filePath);
        var repository = new TicketRepository(dumpManager);

        var commandManager = new CommandManager() {{
            register(Commands.HELP, new Help(this));
            register(Commands.INFO, new Info(repository));
            register(Commands.SHOW, new Show(repository));
            register(Commands.ADD, new Add(repository));
            register(Commands.UPDATE, new Update(repository));
            register(Commands.REMOVE_BY_ID, new RemoveById(repository));
            register(Commands.REMOVE_FIRST, new RemoveFirst(repository));
            register(Commands.REMOVE_LOWER, new RemoveLower(repository));
            register(Commands.COUNT_BY_DISCOUNT, new CountByDiscount(repository));
            register(Commands.FILTER_GREATER_THAN_DISCOUNT, new FilterGreaterThanDiscount(repository));
            register(Commands.PRINT_ASCENDING, new PrintAscending(repository));
            register(Commands.CLEAR, new Clear(repository));
            register(Commands.HEAD, new Head(repository));
        }};
        TCPserver server = new TCPserver("localhost", PORT, new CommandHandler(commandManager));
        server.setSaveCallback((new Save(repository, dirPath + "/out/out_server.txt", true)::saveStringToFile));
        server.start();
    }

    static class JTread extends Thread {
        private final TicketRepository productRepository;

        public JTread(TicketRepository productRepository) {
            super();
            this.productRepository = productRepository;
        }

        public void run() {
            Scanner scan = new Scanner(System.in);
            while (true) {
                String s = scan.nextLine();
                if (s.equals("save")) {
                    try {
                        new Save(productRepository, dirPath + "/out/out_server.txt", false).apply(null);
                    } catch (Exception ignored) {
                    }
                } else {
                    System.err.println("Неизвестная команда");
                }
            }
        }
    }
}