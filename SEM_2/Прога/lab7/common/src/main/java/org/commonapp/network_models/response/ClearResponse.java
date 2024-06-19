package org.commonapp.network_models.response;

import org.commonapp.utility.Commands;

public class ClearResponse extends Response {
  public ClearResponse(String error) {
    super(Commands.CLEAR, error, "");
  }
}
