package org.serverapp.commands;


import org.serverapp.managers.TicketRepository;
import org.commonapp.network_models.request.AddRequest;
import org.commonapp.network_models.request.Request;
import org.commonapp.network_models.response.AddResponse;
import org.commonapp.network_models.response.Response;

/**
 * Команда 'add'. Добавляет новый элемент в коллекцию.
 */
public class Add extends Command {
    private final TicketRepository ticketRepository;

    public Add(TicketRepository ticketRepository) {
        super("add {element}", "добавить новый элемент в коллекцию");
        this.ticketRepository = ticketRepository;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public Response apply(Request request) {
        var req = (AddRequest) request;
        try {
            if (!req.ticket.validate()) {
                return new AddResponse(-1, "Поля продукта не валидны! Продукт не добавлен!");
            }
            var newId = ticketRepository.add(req.ticket);
            return new AddResponse(newId, null);
        } catch (Exception e) {
            return new AddResponse(-1, e.toString());
        }
    }
}
