package org.commonapp.network_models.request;


import org.commonapp.utility.Commands;

public class HeadRequest extends Request {
  public HeadRequest() {
    super(Commands.HEAD);
  }
}
