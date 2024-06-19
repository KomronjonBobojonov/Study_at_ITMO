package org.commonapp.network_models.request;

import org.commonapp.utility.Commands;

public class ClearRequest extends Request {
  public ClearRequest() {
    super(Commands.CLEAR);
  }
}
