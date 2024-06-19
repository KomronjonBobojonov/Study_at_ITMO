package org.clientapp.commands;

import java.util.Objects;

/**
 * Абстрактная команда с именем и описанием
 *  
 */
public abstract class Command {
  private final String name;
//  private final String description;

  public Command(String name) {
    this.name = name;
//    this.description = description;
  }

  /**
   * @return Название и использование команды.
   */
  public String getName() {
    return name;
  }

  public abstract boolean apply(String[] arguments);

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Command command = (Command) o;
    return Objects.equals(name, command.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  @Override
  public String toString() {
    return "Command{" +
      "name='" + name + '\'' +
      '}';
  }
}
