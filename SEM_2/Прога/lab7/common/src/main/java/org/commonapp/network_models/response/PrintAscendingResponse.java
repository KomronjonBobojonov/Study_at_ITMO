package org.commonapp.network_models.response;

import org.commonapp.utility.Commands;

public class PrintAscendingResponse extends Response {
    public PrintAscendingResponse(String error, String result) {
        super(Commands.PRINT_ASCENDING, error, result);
    }
}
