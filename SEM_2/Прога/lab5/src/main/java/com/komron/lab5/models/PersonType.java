package com.komron.lab5.models;

/**
 * Enum class given in task
 * 
 */
public enum PersonType {
    GREEN,
    RED,
    BLUE,
    ORANGE;

    public static String names() {
        StringBuilder nameList = new StringBuilder();
        for (var weaponType : values()) {
            nameList.append(weaponType.name()).append(", ");
        }
        return nameList.substring(0, nameList.length() - 2);
    }
}
