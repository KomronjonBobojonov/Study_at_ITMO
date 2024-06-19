package org.serverapp.commands;

import org.serverapp.managers.TicketRepository;
import org.commonapp.network_models.request.Request;
import org.commonapp.network_models.response.PrintAscendingResponse;
import org.commonapp.network_models.response.Response;

public class PrintAscending extends Command {
    final TicketRepository ticketRepository;

    public PrintAscending(TicketRepository ticketRepository) {
        super("print_ascending", "Выводит элементы коллекции в порядке возрастания.");
        this.ticketRepository = ticketRepository;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public Response apply(Request request) {
        try {
            var response = ticketRepository.printElementsAscending();
            return new PrintAscendingResponse(null, response);
        } catch (Exception e) {
            return new PrintAscendingResponse(null, e.toString());
        }
    }
}
