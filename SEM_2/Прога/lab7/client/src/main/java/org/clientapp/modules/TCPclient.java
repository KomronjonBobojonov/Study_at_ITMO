package org.clientapp.modules;

import org.commonapp.network_models.request.Request;
import org.commonapp.network_models.response.Response;

import java.io.*;
import java.net.*;
import java.nio.channels.*;

public class TCPclient {
    private InetSocketAddress addr;

    private static SocketChannel socketChannel;
    private static ClientReceivingManager receivingManager = null;
    private static ClientSendingManager sendingManager = null;

    public TCPclient(String addr, int port) {
        this.addr = new InetSocketAddress(addr, port);
    }

    public SocketChannel start() {
        System.out.println("Starting the client...");
        while (true) {
            try {
                if (socketChannel != null) {
                    socketChannel.close();
                }
                socketChannel = SocketChannel.open();
                var result = socketChannel.connect(this.addr);
                receivingManager = new ClientReceivingManager(this);
                sendingManager = new ClientSendingManager(this);
                System.out.println("Start function socketChannel conenct: " + result);
                return socketChannel;
            } catch (IOException e) {
                try {
                    System.out.println("TCP client1: " + e.getMessage());
                    socketChannel.close();
                } catch (Exception e2) {
                    System.out.println("TCP client catch: " + e2.getMessage());
                }
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static Response sendCommandAndReceiveResponse(Request request) throws IOException, ClassNotFoundException {
        try (var baos = new ByteArrayOutputStream(); var a = new ObjectOutputStream(baos)) {
            a.writeObject(request);
            sendingManager.send(baos.toByteArray());
            try (var command = new ObjectInputStream(new ByteArrayInputStream(receivingManager.receive()))) {
                Response response = (Response) command.readObject();
                return response;
            }
        } catch (Exception e) {
            System.out.println("\nsendCommand - " + e);
        }
        throw new ClassNotFoundException("");
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

    public boolean isConnected() {
        return socketChannel != null && socketChannel.socket().isBound() && !socketChannel.socket().isClosed() && socketChannel.isConnected()
                && !socketChannel.socket().isInputShutdown() && !socketChannel.socket().isOutputShutdown();
    }
}
