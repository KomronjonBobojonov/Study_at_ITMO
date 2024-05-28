package com.komron.lab5.commands;

/**
 * Интерфейс для всех выполняемых команд.
 * @author Komron
 */
public interface Executable {
  /**
   * Выполнить что-либо.
   *
   * @param arguments Аргумент для выполнения
   * @return результат выполнения
   */
  boolean apply(String[] arguments);
}
