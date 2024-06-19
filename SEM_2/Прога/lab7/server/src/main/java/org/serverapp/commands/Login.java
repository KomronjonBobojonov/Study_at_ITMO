package org.serverapp.commands;

import org.commonapp.network_models.request.LoginRequest;
import org.commonapp.network_models.request.Request;
import org.commonapp.network_models.response.InfoResponse;
import org.commonapp.network_models.response.LoginResponse;
import org.commonapp.network_models.response.Response;
import org.serverapp.managers.DatabaseManager;
import org.serverapp.managers.TicketRepository;
import org.serverapp.modules.AuthRepository;

public class Login extends Command {
    private final AuthRepository authRepository;
    private final DatabaseManager databaseManager;
    private final TicketRepository repository;

    public Login(AuthRepository authRepository, DatabaseManager databaseManager, TicketRepository repository) {
        super("login", " ");
        this.authRepository = authRepository;
        this.databaseManager = databaseManager;
        this.repository = repository;
    }

    @Override
    public Response apply(Request request) {
        var req = (LoginRequest) request;
        var response = authRepository.login(databaseManager, req.user.login, req.user.password);
        System.out.println(response);
        var isOk = response.endsWith("ok");
        if (isOk) repository.loadCollection(req.user.login);
        return new LoginResponse(isOk, response);
    }
}
