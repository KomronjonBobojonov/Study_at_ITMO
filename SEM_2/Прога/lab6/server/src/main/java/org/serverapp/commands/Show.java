package org.serverapp.commands;


import org.serverapp.managers.TicketRepository;
import org.commonapp.network_models.request.Request;
import org.commonapp.network_models.response.Response;
import org.commonapp.network_models.response.ShowResponse;

import java.util.Collections;

/**
 * Команда 'show'. Выводит все элементы коллекции.
 
 */
public class Show extends Command {
    private final TicketRepository ticketRepository;

    public Show(TicketRepository ticketRepository) {
        super("show", "вывести все элементы коллекции");
        this.ticketRepository = ticketRepository;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public Response apply(Request request) {
        try {
            return new ShowResponse(null, ticketRepository.sorted());
        } catch (Exception e) {
            return new ShowResponse(e.toString(), Collections.emptyList());
        }
    }
}
