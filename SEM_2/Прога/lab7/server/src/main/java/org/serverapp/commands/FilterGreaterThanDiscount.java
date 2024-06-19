package org.serverapp.commands;

import org.serverapp.managers.TicketRepository;
import org.commonapp.network_models.request.FilterGreaterThanDiscountRequest;
import org.commonapp.network_models.request.Request;
import org.commonapp.network_models.response.FilterGreaterThanDiscountResponse;
import org.commonapp.network_models.response.Response;

public class FilterGreaterThanDiscount extends Command {
    private final TicketRepository ticketRepository;

    public FilterGreaterThanDiscount(TicketRepository ticketRepository) {
        super("filter_greater_than_discount discount", "вывести элементы, значение поля discount которых больше заданного");
        this.ticketRepository = ticketRepository;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public Response apply(Request request) {
        var req = (FilterGreaterThanDiscountRequest) request;
        try {
            var response = ticketRepository.elementsHigherElementsByDiscount(req.discount);
            return new FilterGreaterThanDiscountResponse(null, response);
        } catch (Exception e) {
            return new FilterGreaterThanDiscountResponse(null, e.toString());
        }
    }
}
