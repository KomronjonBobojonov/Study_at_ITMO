package org.commonapp.network_models.request;


import org.commonapp.utility.Commands;

public class ShowRequest extends Request {
  public ShowRequest() {
    super(Commands.SHOW);
  }
}
