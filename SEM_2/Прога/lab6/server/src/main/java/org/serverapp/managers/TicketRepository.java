package org.serverapp.managers;


import com.google.common.collect.Iterables;
import org.commonapp.exceptions.CollectionIsEmptyException;
import org.serverapp.Main;
import org.commonapp.model.Ticket;
import org.commonapp.model.Venue;
import org.serverapp.utility.TicketComparator;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
/**
 * Класс для управления списком 'Ticket'.
 */
public class TicketRepository {
    private Queue<Ticket> collection = new PriorityQueue<Ticket>();
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;

    private final DumpManager dumpManager;

    public TicketRepository(DumpManager dumpManager) {
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
    /*

     */

    /*
    public void validateAll() {
        (new ArrayList<>(this.getCollection())).forEach(ticket -> {
            if (!ticket.validate()) {
                Main.logger.error("Ticket с id=" + ticket.getId() + " имеет невалидные поля.");
            }
        });
        Main.logger.info("! Загруженные продукты валидны. Size: " + collection.size());
    }
*/
    public boolean validateAll() {
        for (var ticket : new ArrayList<>(get())) {
            if (!ticket.validate()) {
                Main.logger.warn("Ticket с id=" + ticket.getId() + " имеет невалидные поля.");
                return false;
            }
            /*if (ticket.getManufacturer() != null) {
                if (!ticket.getManufacturer().validate()) {
                    Main.logger.warn("Производитель продукта с id=" + ticket.getId() + " имеет невалидные поля.");
                    return false;
                }
            }*/
        }
        Main.logger.info("! Загруженные продукты валидны.");
        return true;
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
     * @return коллекция.
     */
    public Queue<Ticket> get() {
        return collection;
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
     * Removes those tickets from collection which have lower price than given 'tic ket'
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
        if (collection == null || collection.isEmpty()) {
            throw new CollectionIsEmptyException();
        }
        return (int) collection.stream()
                .filter(ticket -> ticket.getDiscount() == discount)
                .count();
    }


    /**
     * Prints Elements which have higher discounts than given discount
     * @param discount
     * @throws CollectionIsEmptyException
     */
    public String elementsHigherElementsByDiscount(int discount) throws CollectionIsEmptyException {
        if (collection == null) {
            throw new CollectionIsEmptyException();
        }

        StringBuilder info = new StringBuilder();
        long amount = collection.stream()
                .filter(ticket -> ticket.getDiscount() > discount)
                .peek(ticket -> info.append(ticket))
                .count();

        info.append("\nResult: ").append(amount);
        return info.toString();
    }


    /**
     * Prints elements by sorting them by their price
     * @throws CollectionIsEmptyException
     */
    public String printElementsAscending() throws CollectionIsEmptyException {
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
        return info.toString();
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

    /**
     * Сохраняет коллекцию в файл
     */
    public void save() {
        dumpManager.writeCollection(collection);
        lastSaveTime = LocalDateTime.now();
    }

    /**
     * @return Имя типа коллекции.
     */
    public String type() {
        return collection.getClass().getName();
    }

    /**
     * @return Размер коллекции.
     */
    public int size() {
        return collection.size();
    }

    /**
     * @return Отсортированная коллекция.
     */
    public List<Ticket> sorted() {
        return new ArrayList<>(collection)
                .stream()
                .sorted(new TicketComparator())
                .collect(Collectors.toList());
    }
    /**
     * Добавляет элемент в коллекцию
     *
     * @param element Элемент для добавления.
     * @return id нового элемента
     */
    public int add(Ticket element) {
        var maxId = collection.stream().filter(Objects::nonNull)
                .map(Ticket::getId)
                .mapToInt(Integer::intValue).max().orElse(0);
        var newId = maxId + 1;

        var nextOrgId = collection.stream()
                .map(Ticket::getVenue)
                .filter(Objects::nonNull)
                .map(Venue::getId)
                .mapToLong(Long::longValue).max().orElse(0) + 1;

        if (element.getVenue() != null) {
            element.getVenue().setId(nextOrgId);
        }
        collection.add(element.copy(newId));
        return newId;
    }


    /**
     * @param id ID элемента.
     * @return Проверяет, существует ли элемент с таким ID.
     */
    public boolean checkExist(int id) {
        return getById(id) != null;
    }

    /**
     * Удаляет элемент из коллекции.
     *
     * @param id ID элемента для удаления.
     */
    public void remove(int id) {
        collection.removeIf(product -> product.getId() == id);
    }

    /**
     * Удаляет элемент из коллекции.
     *
     */
    public void removeFirst() {
        collection.poll();
    }

    /**
     * Очищает коллекцию.
     */
    public void clear() {
        collection.clear();
    }

    /**
     * @return Первый элемент коллекции (null если коллекция пустая).
     */
    public Ticket first() {
        if (collection.isEmpty()) return null;
        return sorted().get(0);
    }
}
