package org.commonapp.network_models.request;


import org.commonapp.utility.Commands;

public class InfoRequest extends Request {
  public InfoRequest() {
    super(Commands.INFO);
  }
}
