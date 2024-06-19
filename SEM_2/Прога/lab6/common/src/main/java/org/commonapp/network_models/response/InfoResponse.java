package org.commonapp.network_models.response;


import org.commonapp.utility.Commands;

public class InfoResponse extends Response {
  public final String infoMessage;

  public InfoResponse(String infoMessage, String error) {
    super(Commands.INFO, error, "");
    this.infoMessage = infoMessage;
  }
}
