package org.serverapp.commands;

import org.serverapp.managers.TicketRepository;
import org.commonapp.model.Ticket;
import org.commonapp.network_models.request.Request;
import org.commonapp.network_models.response.Response;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Save extends Command {
    private final TicketRepository productRepository;
    private String filePath;
    private Boolean isAuto;

    public Save(TicketRepository productRepository, String filePath, Boolean isAuto) {
        super("save", "save data locally");
        this.productRepository = productRepository;
        this.isAuto = isAuto;
        this.filePath = filePath;
    }

    @Override
    public Response apply(Request request) {
        return null;
    }

    public void saveStringToFile() {
        StringBuilder str = new StringBuilder();
        for (Ticket t : productRepository.getCollection()) {
            str.append(t.toString());
        }
        var text = str.toString();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(text);
            if (!isAuto)
                System.out.println("[Server]: Изменения сохранены.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
