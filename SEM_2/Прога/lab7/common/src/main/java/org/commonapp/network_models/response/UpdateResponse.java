package org.commonapp.network_models.response;


import org.commonapp.utility.Commands;

public class UpdateResponse extends Response {
  public UpdateResponse(String error) {
    super(Commands.UPDATE, error, "");
  }
}
