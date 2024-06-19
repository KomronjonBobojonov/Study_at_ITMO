package org.serverapp.commands;


import org.serverapp.managers.TicketRepository;
import org.commonapp.network_models.request.RemoveByIdRequest;
import org.commonapp.network_models.request.Request;
import org.commonapp.network_models.response.RemoveByIdResponse;
import org.commonapp.network_models.response.Response;

/**
 * Команда 'remove_by_id'. Удаляет элемент из коллекции.
 *  
 */
public class RemoveById extends Command {
    private final TicketRepository productRepository;

    public RemoveById(TicketRepository productRepository) {
        super("remove_by_id <ID>", "удалить элемент из коллекции по ID");
        this.productRepository = productRepository;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public Response apply(Request request) {
        var req = (RemoveByIdRequest) request;

        try {
            if (!productRepository.checkExist(req.id)) {
                return new RemoveByIdResponse("Продукта с таким ID в коллекции нет!");
            }

            var response = productRepository.remove(req.id, req.login);
            if(response) return new RemoveByIdResponse(null);
            else return new RemoveByIdResponse("OShibka");
        } catch (Exception e) {
            return new RemoveByIdResponse(e.toString());
        }
    }
}
