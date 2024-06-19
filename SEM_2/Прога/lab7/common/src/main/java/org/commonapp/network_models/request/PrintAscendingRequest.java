package org.commonapp.network_models.request;


import org.commonapp.utility.Commands;

public class PrintAscendingRequest extends Request {
    public PrintAscendingRequest() {
        super(Commands.PRINT_ASCENDING);
    }
}
