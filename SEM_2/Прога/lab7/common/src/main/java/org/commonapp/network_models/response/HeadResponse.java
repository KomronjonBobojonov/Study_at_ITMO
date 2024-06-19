package org.commonapp.network_models.response;


import org.commonapp.utility.Commands;
import org.commonapp.model.Ticket;

public class HeadResponse extends Response {
  public final Ticket ticket;

  public HeadResponse(Ticket ticket, String error) {
    super(Commands.HEAD, error, "");
    this.ticket = ticket;
  }
}
