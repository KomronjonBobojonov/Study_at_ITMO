package org.commonapp.model;

import java.io.Serializable;

/**
 * Class given in task
 
 */
public class Venue implements Serializable {
    private static Long nextId = 1L;
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private long capacity; //Значение поля должно быть больше 0
    private VenueType type; //Поле может быть null

    public Venue(
            String name,
            long capacity,
            VenueType type
    ) {
        id = nextId;
        this.capacity = capacity;
        this.name = name;
        this.type = type;
        nextId++;
    }

    public boolean validate() {
        return (id != null && name != null && !name.isEmpty() && capacity > 0 && type != null);
    }

    @Override
    public String toString() {
        String info = "";
        info += "Venue №" + id;
        info += "\n Название: " + name;
        info += "\n capacity: " + capacity;
        info += "\n type:\n    " + type;
        return info;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}