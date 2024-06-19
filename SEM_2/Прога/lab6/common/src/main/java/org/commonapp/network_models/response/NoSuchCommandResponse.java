package org.commonapp.network_models.response;

public class NoSuchCommandResponse extends Response {
  public NoSuchCommandResponse(String name) {
    super(name, "No such command", "");
  }
}
