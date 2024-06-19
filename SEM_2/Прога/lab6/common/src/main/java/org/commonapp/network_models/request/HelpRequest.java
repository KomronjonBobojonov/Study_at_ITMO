package org.commonapp.network_models.request;


import org.commonapp.utility.Commands;

public class HelpRequest extends Request {
  public HelpRequest() {
    super(Commands.HELP);
  }
}
