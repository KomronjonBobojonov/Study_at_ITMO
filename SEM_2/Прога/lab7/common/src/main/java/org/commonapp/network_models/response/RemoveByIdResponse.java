package org.commonapp.network_models.response;


import org.commonapp.utility.Commands;

public class RemoveByIdResponse extends Response {
  public RemoveByIdResponse(String error) {
    super(Commands.REMOVE_BY_ID, error, "");
  }
}
