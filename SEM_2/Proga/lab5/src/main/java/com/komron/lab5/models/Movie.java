package com.komron.lab5.models;

import com.komron.lab5.managers.CollectionManager;
import com.komron.lab5.utility.Element;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Class given in task
 * 
 */
public class Movie extends Element {

    private static long nextId = 1;

    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private double price; //Значение поля должно быть больше 0
    private int discount; //Значение поля должно быть больше 0, Максимальное значение поля: 100
    private Boolean refundable; //Поле может быть null
    private MovieGenre type; //Поле может быть null
    private Person Person; //Поле не может быть null

    ArrayList<Integer> formula = new ArrayList<>(Arrays.asList(1,2,30));

    public Movie(
            String name,
            Coordinates coordinates,
            LocalDate creationDate,
            double price,
            int discount,
            Boolean refundable,
            MovieGenre type,
            Person Person
    ) {
        this.id = nextId;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.price = price;
        this.discount = discount;
        this.refundable = refundable;
        this.type = type;
        this.Person = Person;
    }

    /**
     * Обновляет указатель следующего ID
     *
     * @param collectionManager манагер коллекций
     */
    public static void updateNextId(CollectionManager collectionManager) {
        var maxId = collectionManager
                .getCollection()
                .stream().filter(Objects::nonNull)
                .map(Movie::getId)
                .mapToLong(Long::longValue).max().orElse(0);
        nextId = maxId + 1;
    }

    public static void touchNextId() {
        nextId++;
    }

    public double getPrice() {
        return price;
    }

    public int getDiscount() {
        return discount;
    }

    /**
     * Валидирует правильность полей.
     *
     * @return true, если все верно, иначе false
     */
    @Override
    public boolean validate() {
        if (id <= 0) return false;
        if (name == null || name.isEmpty()) return false;
        if (coordinates == null) return false;
        if (creationDate == null) return false;
        if (price <= 0) return false;
        if (discount <= 0 || discount > 100) return false;
        if (refundable == null) return false;
        return Person != null;
    }
    public void update(Movie Movie) {
        this.name = Movie.name;
        this.coordinates = Movie.coordinates;
        this.creationDate = Movie.creationDate;
        this.price = Movie.price;
        this.discount = Movie.discount;
        this.Person = Movie.Person;
        this.refundable = Movie.refundable;

    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public int compareTo(Element element) {
        return (int) (this.id - element.getId());
    }

    @Override
    public String toString() {
        String info = "";
        info += "Movie №" + id;
        info += " (добавлен " + creationDate.toString() + ")";
        info += "\n Название: " + name;
        info += "\n Местоположение: " + coordinates;
        info += "\n Цена: " + price;
        info += "\n discount: " + discount;
        info += "\n is refundable: " + refundable;
        info += "\n type:\n    " + type;
        info += "\n Person:\n    " + Person;
        return info;
    }
}

