package enums;

public enum Direction {
    AHEAD("впереди"), BEHIND("сзади"), LEFT("слева"), RIGHT("справа");
    private final String name;

    Direction(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
