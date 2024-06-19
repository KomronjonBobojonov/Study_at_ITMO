package org.serverapp.commands;


import org.serverapp.managers.TicketRepository;
import org.commonapp.network_models.request.Request;
import org.commonapp.network_models.response.RemoveFirstResponse;
import org.commonapp.network_models.response.Response;

/**
 * Команда 'remove_by_id'. Удаляет элемент из коллекции.
 
 */
public class RemoveFirst extends Command {
    private final TicketRepository productRepository;

    public RemoveFirst(TicketRepository productRepository) {
        super("remove_first", "удалить элемент из коллекции по ID");
        this.productRepository = productRepository;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public Response apply(Request request) {
        try {
            if (productRepository.size() == 0) {
                return new RemoveFirstResponse("No ticket to delete!");
            }
            productRepository.removeFirst();
            return new RemoveFirstResponse(null);
        } catch (Exception e) {
            return new RemoveFirstResponse(e.toString());
        }
    }
}
