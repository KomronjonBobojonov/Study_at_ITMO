package org.commonapp.network_models.request;

import org.commonapp.utility.Commands;

public class CountByDiscountRequest  extends Request {
    public final int discount;
    public CountByDiscountRequest(int discount) {
        super(Commands.COUNT_BY_DISCOUNT);
        this.discount = discount;
    }
}
