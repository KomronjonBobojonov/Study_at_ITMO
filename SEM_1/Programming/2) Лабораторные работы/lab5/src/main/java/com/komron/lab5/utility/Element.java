package com.komron.lab5.utility;

/**
 * Полезный абстрактный класс для класса, указанного в задаче.
 * @author Komron
 */
public abstract class Element implements Comparable<Element>, Validatable {
  abstract public long getId();
}
