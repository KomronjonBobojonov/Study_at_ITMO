package org.serverapp.managers;


import com.google.common.collect.Iterables;
import org.commonapp.exceptions.CollectionIsEmptyException;
import org.commonapp.model.*;
import org.serverapp.Main;
import org.serverapp.utility.TicketComparator;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * Класс для управления списком 'Ticket'.
 *  
 */
public class TicketRepository {

    private Queue<Ticket> collection = new PriorityQueue<Ticket>();
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;

    private final ReentrantLock lock = new ReentrantLock();

    private final DumpManager dumpManager;
    private final DatabaseManager databaseManager;


    public TicketRepository(DumpManager dumpManager, DatabaseManager databaseManager) {
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.dumpManager = dumpManager;
        this.databaseManager = databaseManager;
    }

    /**
     * Loads collection of type 'Ticket'
     */
    public void loadCollection(String login) {
        select(login);

        /*collection = (PriorityQueue<Ticket>) dumpManager.readCollection();
        collection.forEach(ticket -> {
            addLocally(ticket, login);
        });*/
        lastInitTime = LocalDateTime.now();
    }

    public void select(String login) {
        lock.lock();
        try {
            var userId = 0;
            userId = getUserIdByLogin(login);
            ResultSet rs = databaseManager.getStatement().executeQuery("SELECT * FROM Ticket WHERE \"ownerid\" = '" + userId + "';");
            while (rs.next()) {
                try {
                    Integer id = rs.getInt("id");
                    String name = rs.getString("name");
                    Coordinates coordinates = new Coordinates(rs.getLong("cor_x"), rs.getDouble("cor_y"));
                    LocalDate creationDate = (rs.getString("creationDate") == null) ? null : LocalDate.parse(rs.getString("creationDate"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    double price = rs.getDouble("price");
                    int discount = rs.getInt("discount");
                    boolean refundable = rs.getBoolean("refundable");
                    TicketType ticketType = (rs.getString("typeTicket") == null) ? null : TicketType.valueOf(rs.getString("typeTicket"));
                    int venueId = rs.getInt("venueId");
                    Venue venue = getVenueFromDbById(venueId);
                    collection.add(new Ticket(id, name, coordinates, creationDate, price, discount, refundable, ticketType, venue));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        lock.unlock();
    }

    public Venue getVenueFromDbById(int id) throws SQLException {
        lock.lock();

        ResultSet rs = databaseManager.getStatement().executeQuery("SELECT * FROM Venue WHERE \"id\" = '" + id + "' LIMIT 1;");
        String name = null;
        long capacity = 0;
        VenueType type = null;
        while (rs.next()) {
            name = rs.getString("name");
            capacity = rs.getLong("capacity");
            type = (rs.getString("typeVenue") == null) ? null : VenueType.valueOf(rs.getString("typeVenue"));
        }
        lock.unlock();

        return new Venue(name, capacity, type);
    }

    /**
     * @return коллекция.
     */
    public Queue<Ticket> getCollection() {
        return collection;
    }

    public boolean validateAll() {
        for (var ticket : new ArrayList<>(get())) {
            if (!ticket.validate()) {
                Main.logger.warn("Ticket с id=" + ticket.getId() + " имеет невалидные поля.");
                return false;
            }
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
    public void removeLowerPricedTickets(Ticket referenceTicket, String login) throws CollectionIsEmptyException {
        if (collection == null || referenceTicket == null) {
            throw new CollectionIsEmptyException();
        }
        Iterator<Ticket> iterator = collection.iterator();
        while (iterator.hasNext()) {
            Ticket ticket = iterator.next();
            if (ticket.getPrice() < referenceTicket.getPrice()) {
                remove(ticket.getId(), login);
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
    public String elementsHigherElementsByDiscount(int discount) throws CollectionIsEmptyException {
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
    public int size(String login) {
        // TODO
        ResultSet rs = null;
        int userId = 0;
        int response = 0;
        try {
            rs = databaseManager.getStatement().executeQuery("SELECT id FROM Users WHERE \"login\" = '" + login + "';");
            while (rs.next()) {
                userId = rs.getInt("id");
            }
            rs = databaseManager.getStatement().executeQuery("SELECT COUNT(*) as count FROM ticket WHERE \"ownerid\" = '" + userId + "';");
            while (rs.next()) {
                response = rs.getInt("count");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return response; // collection.size();
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
    public int add(Ticket element, String login) {
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
        var newElement = element.copy(newId);
        Integer ticketId = addLocally(newElement, login);
        if (ticketId != null)
            newElement = newElement.copy(ticketId);
        collection.add(newElement);
        return newId;
    }

    private Integer addLocally(Ticket ticket, String login) {
        lock.lock();
        Integer ticketId = null;
        try {
            int venueId = 0;
            int userId = 0;
            int counterParams = 1;
            PreparedStatement venueStatement = databaseManager.getPreparedStatementRGK("INSERT INTO Venue (" +
                    "name, capacity, typeVenue) " +
                    "VALUES (?, ?, CAST(? AS venue_type));");

            venueStatement.setString(counterParams++, ticket.getVenue().getName());
            venueStatement.setLong(counterParams++, ticket.getVenue().getCapacity());
            venueStatement.setString(counterParams++, ticket.getVenue().getType().name());
            if (venueStatement.executeUpdate() == 0) {
                throw new RuntimeException("Error in execute");
            }
            ResultSet gk = venueStatement.getGeneratedKeys();
            if (gk.next()) {
                venueId = gk.getInt(1);
            }
            ResultSet rs = databaseManager.getStatement().executeQuery("SELECT id FROM Users WHERE \"login\" = '" + login + "';");
            while (rs.next()) {
                userId = rs.getInt("id");
            }
            counterParams = 1;
            PreparedStatement ticketStatement = databaseManager.getPreparedStatementRGK("INSERT INTO Ticket (" +
                    "name, cor_x, cor_y, creationDate, price, discount, refundable, typeTicket, venueId, ownerId) " +
                    "VALUES (?, ?, ?, ?, ?, ?,?, CAST(? AS ticket_type), ?, ?);");
            ticketStatement.setString(counterParams++, ticket.getName());
            ticketStatement.setLong(counterParams++, ticket.getCoordinates().getX());
            ticketStatement.setDouble(counterParams++, ticket.getCoordinates().getY());
            ticketStatement.setString(counterParams++, ticket.getCreationDate().toString());
            ticketStatement.setDouble(counterParams++, ticket.getPrice());
            ticketStatement.setInt(counterParams++, ticket.getDiscount());
            ticketStatement.setBoolean(counterParams++, ticket.getRefundable());
            ticketStatement.setString(counterParams++, ticket.getType().name());
            ticketStatement.setInt(counterParams++, venueId);
            ticketStatement.setInt(counterParams++, userId);
            if (ticketStatement.executeUpdate() == 0) {
                throw new RuntimeException("Error in execute");
            }
            gk = ticketStatement.getGeneratedKeys();
            if (gk.next()) {
                ticketId = gk.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        lock.unlock();
        return ticketId;
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
    public boolean remove(int id, String login) {
        try {
            if(removeLocally(id, login)) {
                collection.removeIf(product -> product.getId() == id);
                return true;
            }else {
                System.out.println("error while deleting the element");
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean removeLocally(int id, String login) throws SQLException {
        Integer userId = getUserIdByLogin(login);
        try {
            PreparedStatement delete = databaseManager.getPreparedStatement("DELETE FROM Ticket WHERE id = ? AND ownerid = ?;");
            delete.setInt(1, id);
            delete.setInt(2, userId);
            if (delete.executeUpdate() == 0) {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    /**
     * Удаляет элемент из коллекции.
     *
     */
    public void removeFirst(String login) {
        if (collection.peek() != null) {
            remove(collection.peek().getId(), login);
        }else {
            System.out.println("List null");
        }
    }

    /**
     * Очищает коллекцию.
     */
    public void clear(String login) {
        try {
            var userid = getUserIdByLogin(login);
            ResultSet rs = databaseManager.getStatement().executeQuery("DELETE FROM ticket WHERE \"ownerid\" = '" + userid + "';");

            select(login);
            /*collection.stream().map(),,*/
            // collection.clear();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public Integer getUserIdByLogin(String login) throws SQLException {
        ResultSet rs = databaseManager.getStatement().executeQuery("SELECT id FROM Users WHERE \"login\" = '" + login + "';");
        Integer userId = null;
        while (rs.next()) {
            userId = rs.getInt("id");
        }
        return userId;
    }
    /**
     * @return Первый элемент коллекции (null если коллекция пустая).
     */
    public Ticket first() {
        if (collection.isEmpty()) return null;
        return sorted().get(0);
    }
}
