package org.serverapp.commands;


import org.serverapp.managers.TicketRepository;
import org.commonapp.network_models.request.Request;
import org.commonapp.network_models.request.UpdateRequest;
import org.commonapp.network_models.response.Response;
import org.commonapp.network_models.response.UpdateResponse;

/**
 * Команда 'update'. Обновляет элемент коллекции.
 
 */
public class Update extends Command {
    private final TicketRepository ticketRepository;

    public Update(TicketRepository ticketRepository) {
        super("update <ID> {element}", "обновить значение элемента коллекции по ID");
        this.ticketRepository = ticketRepository;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public Response apply(Request request) {
        var req = (UpdateRequest) request;
        try {
            if (!ticketRepository.checkExist(req.id)) {
                return new UpdateResponse("Продукта с таким ID в коллекции нет!");
            }
            if (!req.updatedTicket.validate()) {
                return new UpdateResponse("Поля продукта не валидны! Продукт не обновлен!");
            }

            ticketRepository.getById(req.id).update(req.updatedTicket);
            return new UpdateResponse(null);
        } catch (Exception e) {
            return new UpdateResponse(e.toString());
        }
    }
}
