package com.komron.lab5.managers;


import com.google.common.collect.Iterables;
import com.komron.lab5.exceptions.CollectionIsEmptyException;
import com.komron.lab5.models.Movie;
import com.komron.lab5.utility.console.Console;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс для управления списком 'Movie'.
 *  
 */
public class CollectionManager {
    private Queue<Movie> collection = new PriorityQueue<Movie>();
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;

    private final DumpManager dumpManager;

    public CollectionManager(DumpManager dumpManager) {
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.dumpManager = dumpManager;

        loadCollection();
    }

    /**
     * Loads collection of type 'Movie'
     */
    private void loadCollection() {
        collection = (PriorityQueue<Movie>) dumpManager.readCollection();
        lastInitTime = LocalDateTime.now();
    }

    /**
     * @return коллекция.
     */
    public Queue<Movie> getCollection() {
        return collection;
    }

    /**
     * Validates data entered
     * @param console
     */
    public void validateAll(Console console) {
        (new ArrayList<>(this.getCollection())).forEach(Movie -> {
            if (!Movie.validate()) {
                console.printError("Movie с id=" + Movie.getId() + " имеет невалидные поля.");
            }
        });
        console.println("! Загруженные продукты валидны. Size: " + collection.size());
    }

    /**
     * @return Последнее время инициализации.
     */
    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }

    /**
     * @return Последнее время сохранения.
     */
    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }

    /**
     * @return Имя типа коллекции.
     */
    public String collectionType() {
        return collection.getClass().getName();
    }

    /**
     * @return Размер коллекции.
     */
    public int collectionSize() {
        return collection.size();
    }

    /**
     * Добавляет элемент в коллекцию
     *
     * @param element Элемент для добавления.
     */
    public void addToCollection(Movie element) {
        collection.add(element);
        Movie.touchNextId();
    }

    /**
     * @return Последний элемент коллекции (null если коллекция пустая).
     */
    public Movie getLast() {
        if (collection.isEmpty()) return null;
        return Iterables.getLast(collection);
    }

    /**
     * @param id
     * @return 'Movie' with id specified
     */
    public Movie getById(int id) {
        for (Movie element : collection) {
            if (element.getId() == id) return element;
        }
        return null;
    }

    /**
     * Saves collection to the file specified
     */
    public void saveCollection() {
        dumpManager.writeCollection(collection);
        lastSaveTime = LocalDateTime.now();
    }

    /**
     * @return Пер элемент коллекции (null если коллекция пустая).
     */
    public Movie getFirst() {
        if (collection.isEmpty()) return null;
        return collection.peek();
    }

    /**
     * Removes element from collection
     * @param element
     */
    public void removeFromCollection(Movie element) {
        collection.remove(element);
    }

    /**
     * Clears collection
     */
    public void clearCollection() {
        collection.clear();
    }

    /**
     * Removes those Movies from collection which have lower price than given 'Movie'
     * @param referenceMovie
     * @throws CollectionIsEmptyException
     */
    public void removeLowerPricedMovies(Movie referenceMovie) throws CollectionIsEmptyException {
        if (collection == null || referenceMovie == null) {
            throw new CollectionIsEmptyException();
        }
        Iterator<Movie> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Movie Movie = iterator.next();
            if (Movie.getPrice() < referenceMovie.getPrice()) {
                iterator.remove();
            }
        }
    }

    /**
     * @param discount
     * @return Amount of elements which equals to discount given
     * @throws CollectionIsEmptyException
     */
    public int getAmountOfEqualsElementsByDiscount(int discount) throws CollectionIsEmptyException {
        if (collection == null) {
            throw new CollectionIsEmptyException();
        }
        int ans = 0;
        Iterator<Movie> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Movie Movie = iterator.next();
            if (Movie.getDiscount() == discount) {
                ans++;
            }
        }
        return ans;
    }

    /**
     * Prints Elements which have higher discounts than given discount
     * @param discount
     * @throws CollectionIsEmptyException
     */
    public void printElementsHigherElementsByDiscount(int discount) throws CollectionIsEmptyException {
        if (collection == null) {
            throw new CollectionIsEmptyException();
        }
        StringBuilder info = new StringBuilder();
        Iterator<Movie> iterator = collection.iterator();
        var amount = 0;
        while (iterator.hasNext()) {
            Movie Movie = iterator.next();
            if (Movie.getDiscount() > discount) {
                info.append(Movie);
                amount++;
            }
        }
        System.out.println("Result: " + amount);
        System.out.println(info);
    }

    /**
     * Prints elements by sorting them by their price
     * @throws CollectionIsEmptyException
     */
    public void printElementsAscending() throws CollectionIsEmptyException {
        if (collection == null) {
            throw new CollectionIsEmptyException();
        }
        StringBuilder info = new StringBuilder();
        Queue<Movie> sortedQueue = collection.stream()
                .sorted(Comparator.comparingDouble(Movie::getPrice))
                .collect(Collectors.toCollection(LinkedList::new));

        Iterator<Movie> iterator = sortedQueue.iterator();
        while (iterator.hasNext()) {
            Movie Movie = iterator.next();
            info.append(Movie);
        }
        System.out.println(info);
    }

    @Override
    public String toString() {
        if (collection.isEmpty()) return "Коллекция пуста!";
        var last = getLast();

        StringBuilder info = new StringBuilder();
        for (Movie product : collection) {
            info.append(product);
            if (product != last) info.append("\n\n");
        }
        return info.toString();
    }

}
