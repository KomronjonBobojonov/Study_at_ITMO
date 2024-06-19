package org.serverapp.commands;


import org.commonapp.network_models.request.Request;
import org.commonapp.network_models.response.Response;

/**
 * Интерфейс для всех выполняемых команд.
 
 */
public interface Executable {
  /**
   * Выполнить что-либо.
   * @param request запрос с данными для выполнения команды
   * @return результат выполнения
   */
  Response apply(Request request);
}
