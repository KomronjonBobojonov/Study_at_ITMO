package enums;

public enum Direction {
    Ahead("впереди"), Behind("сзади"), Left("слева"), Right("справа");
    private final String name;

    Direction(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
