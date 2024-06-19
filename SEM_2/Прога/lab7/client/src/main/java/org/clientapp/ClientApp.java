package org.clientapp;

import org.clientapp.command_line.Runner;
import org.clientapp.command_line.console.Console;
import org.clientapp.command_line.console.StandardConsole;
import org.clientapp.modules.AppModule;
import org.clientapp.modules.TCPclient;

public class ClientApp {
    public static final int PORT = 21381;

    public static void main(String[] args) {
        var client = new TCPclient("localhost", PORT);
        client.start();
        Console console = new StandardConsole();
        AppModule.setClient(client);
        var cli = new Runner(client, console);

        cli.interactiveMode();

    }
}