package org.serverapp.commands;


import org.serverapp.managers.TicketRepository;
import org.commonapp.network_models.request.CountByDiscountRequest;
import org.commonapp.network_models.request.Request;
import org.commonapp.network_models.response.CountByDiscountResponse;
import org.commonapp.network_models.response.Response;

/**
 * Команда 'remove_by_id'. Удаляет элемент из коллекции.
 *  
 */
public class CountByDiscount extends Command {
    private final TicketRepository productRepository;

    public CountByDiscount(TicketRepository productRepository) {
        super("count_by_discount discount", "вывести количество элементов, значение поля discount которых равно заданному");
        this.productRepository = productRepository;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public Response apply(Request request) {
        try {
            var req = (CountByDiscountRequest) request;
            var ans = productRepository.getAmountOfEqualsElementsByDiscount(req.discount);
            return new CountByDiscountResponse(null, Integer.toString(ans));
        } catch (Exception e) {
            return new CountByDiscountResponse(e.toString(), "Error");
        }
    }
}
