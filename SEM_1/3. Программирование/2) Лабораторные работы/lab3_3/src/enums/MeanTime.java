package enums;

public enum MeanTime {
    TimeT("тем временем");

    private final String name;

    MeanTime(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
