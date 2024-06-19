package org.clientapp.blanks;


import org.commonapp.exceptions.IncorrectInputInScriptException;
import org.commonapp.exceptions.InvalidFormException;

/**
 * Абстрактный класс формы для ввода пользовательских данных.
 * @param <T> создаваемый объект
 
 */
public abstract class Form<T> {
  public abstract T build() throws IncorrectInputInScriptException, InvalidFormException;
}
