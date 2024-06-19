package org.clientapp.modules;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Objects;

public class ClientReceivingManager {

    private byte[] receivedData;
    private final int DATA_CHUNK = 1024;
    private final TCPclient tcpClient;

    public ClientReceivingManager(TCPclient tcpClient) {
        this.tcpClient = tcpClient;
        this.receivedData = new byte[0];
    }

    public byte[] receive() throws InterruptedException {
        receivedData = new byte[0];
        while (true) {
            try {
                if (tcpClient.getSocketChannel() == null) {
                    tcpClient.start();
                }
                while (tcpClient.getSocketChannel().isConnectionPending()) {
                    tcpClient.getSocketChannel().finishConnect();
                }
                if (!tcpClient.getSocketChannel().isOpen()) {
                    tcpClient.start();
                }
                ByteBuffer byteBuffer = ByteBuffer.allocate(DATA_CHUNK);
                var readBytes = tcpClient.getSocketChannel().read(byteBuffer);
                if (readBytes == 0) {
                    System.out.println("Readbytes = 0");
                    Thread.sleep(50);
                    continue;
                }
                if (readBytes == -1) {
                    System.out.println("Socket channel closed, readBytes = -1");
                    tcpClient.getSocketChannel().close();
                }

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                outputStream.write(receivedData);
                outputStream.write(Arrays.copyOf(byteBuffer.array(), byteBuffer.array().length - 1));
                receivedData = outputStream.toByteArray();
                if (byteBuffer.array()[readBytes - 1] == 1) {
                    return receivedData;
                }
                byteBuffer.clear();
            } catch (IOException e) {
                System.out.println("Receive exception: " + e.getMessage());
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}