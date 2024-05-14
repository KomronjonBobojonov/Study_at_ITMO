package com.komron.lab5.managers;


import com.google.common.collect.Iterables;
import com.komron.lab5.exceptions.CollectionIsEmptyException;
import com.komron.lab5.models.Ticket;
import com.komron.lab5.utility.console.Console;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс для управления списком 'Ticket'.
 * @author Komron
 */
public class CollectionManager {
    private Queue<Ticket> collection = new PriorityQueue<Ticket>();
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
     * Loads collection of type 'Ticket'
     */
    private void loadCollection() {
        collection = (PriorityQueue<Ticket>) dumpManager.readCollection();
        lastInitTime = LocalDateTime.now();
    }

    /**
     * @return коллекция.
     */
    public Queue<Ticket> getCollection() {
        return collection;
    }

    /**
     * Validates data entered
     * @param console
     */
    public void validateAll(Console console) {
        (new ArrayList<>(this.getCollection())).forEach(ticket -> {
            if (!ticket.validate()) {
                console.printError("Ticket с id=" + ticket.getId() + " имеет невалидные поля.");
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
    public void addToCollection(Ticket element) {
        collection.add(element);
        Ticket.touchNextId();
    }

    /**
     * @return Последний элемент коллекции (null если коллекция пустая).
     */
    public Ticket getLast() {
        if (collection.isEmpty()) return null;
        return Iterables.getLast(collection);
    }

    /**
     * @param id
     * @return 'Ticket' with id specified
     */
    public Ticket getById(int id) {
        for (Ticket element : collection) {
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
    public Ticket getFirst() {
        if (collection.isEmpty()) return null;
        return collection.peek();
    }

    /**
     * Removes element from collection
     * @param element
     */
    public void removeFromCollection(Ticket element) {
        collection.remove(element);
    }

    /**
     * Clears collection
     */
    public void clearCollection() {
        collection.clear();
    }

    /**
     * Removes those tickets from collection which have lower price than given 'ticket'
     * @param referenceTicket
     * @throws CollectionIsEmptyException
     */
    public void removeLowerPricedTickets(Ticket referenceTicket) throws CollectionIsEmptyException {
        if (collection == null || referenceTicket == null) {
            throw new CollectionIsEmptyException();
        }
        Iterator<Ticket> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Ticket ticket = iterator.next();
            if (ticket.getPrice() < referenceTicket.getPrice()) {
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
        Iterator<Ticket> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Ticket ticket = iterator.next();
            if (ticket.getDiscount() == discount) {
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
        Iterator<Ticket> iterator = collection.iterator();
        var amount = 0;
        while (iterator.hasNext()) {
            Ticket ticket = iterator.next();
            if (ticket.getDiscount() > discount) {
                info.append(ticket);
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
        Queue<Ticket> sortedQueue = collection.stream()
                .sorted(Comparator.comparingDouble(Ticket::getPrice))
                .collect(Collectors.toCollection(LinkedList::new));

        Iterator<Ticket> iterator = sortedQueue.iterator();
        while (iterator.hasNext()) {
            Ticket ticket = iterator.next();
            info.append(ticket);
        }
        System.out.println(info);
    }

    @Override
    public String toString() {
        if (collection.isEmpty()) return "Коллекция пуста!";
        var last = getLast();

        StringBuilder info = new StringBuilder();
        for (Ticket product : collection) {
            info.append(product);
            if (product != last) info.append("\n\n");
        }
        return info.toString();
    }

}
