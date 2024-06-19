package org.serverapp.modules;

import org.serverapp.Main;
import org.serverapp.commands.Save;
import org.serverapp.handlers.CommandHandler;
import org.commonapp.network_models.request.Request;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class TCPserver {

    private int port;
    private String host;
    private HashSet<SocketChannel> sessions;
    private ReceivingManager receivingManager = new ReceivingManager();
    private SendingManager sendingManager = new SendingManager();
    private CommandHandler commandHandler;
    private Selector selector;

    private Runnable saveCallback;

    public TCPserver(String host, int port, CommandHandler commandHandler) {
        this.port = port;
        this.host = host;
        this.commandHandler = commandHandler;
        this.sessions = new HashSet<>();
    }

    public HashSet<SocketChannel> getSessions() {
        return sessions;
    }

    public void close() {
        for (var se : sessions) {
            try {
                se.close();
            } catch (Exception e) {
            }
        }
    }

    public void start() {
        try {
            selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            var socketAddress = new InetSocketAddress(host, port);
            serverSocketChannel.bind(socketAddress, 50);
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                selector.select();
                Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                while (keys.hasNext()) {
                    SelectionKey key = keys.next();
                    keys.remove();
                    if (!key.isValid()) continue;
                    if (key.isAcceptable()) {
                        accept(key);
                    } else if (key.isReadable()) {
                        var result = receivingManager.read(key);
                        if (result == null)
                            continue;
                        int p = -1;
                        for (int i = result.length - 1; i > -1; i--) {
                            if (result[i] != 0) {
                                p = i;
                                break;
                            }
                        }
                        var cutres = Arrays.copyOfRange(result, 0, p + 1);

                        try (var command = new ObjectInputStream(new ByteArrayInputStream(cutres))) {
                            Request request = (Request) command.readObject();
                            System.out.println(request.getName());
                            try (var baos = new ByteArrayOutputStream(); var a = new ObjectOutputStream(baos)) {
                                var response = commandHandler.handle(request);
                                a.writeObject(response);
                                sendingManager.send((SocketChannel) key.channel(), baos.toByteArray());
                                if (saveCallback != null) {
//                                    saveCallback.run();
                                }
                            }
                        } catch (Exception e) {
                            sendingManager.send((SocketChannel) key.channel(), "503".getBytes());
                        }
                    }
                }
            }
        } catch (IOException e) {
            Main.logger.error("Could not run the server: " + e.getMessage());
        }
    }

    private void accept(SelectionKey key) {
        try {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            SocketChannel channel = serverSocketChannel.accept();
            channel.configureBlocking(false);
            channel.register(selector, SelectionKey.OP_READ);
            sessions.add(channel);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void setSaveCallback(Runnable saveCallback) {
        this.saveCallback = saveCallback;
    }
}