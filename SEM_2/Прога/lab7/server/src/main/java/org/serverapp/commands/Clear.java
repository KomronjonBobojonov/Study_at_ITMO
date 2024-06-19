package org.serverapp.commands;


import org.serverapp.managers.TicketRepository;
import org.commonapp.network_models.request.Request;
import org.commonapp.network_models.response.ClearResponse;
import org.commonapp.network_models.response.Response;

/**
 * Команда 'clear'. Очищает коллекцию.
 *  
 */
public class Clear extends Command {
    private final TicketRepository productRepository;

    public Clear(TicketRepository productRepository) {
        super("clear", "очистить коллекцию");
        this.productRepository = productRepository;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public Response apply(Request request) {
        try {
            productRepository.clear(request.login);
            return new ClearResponse(null);
        } catch (Exception e) {
            return new ClearResponse(e.toString());
        }
    }
}
