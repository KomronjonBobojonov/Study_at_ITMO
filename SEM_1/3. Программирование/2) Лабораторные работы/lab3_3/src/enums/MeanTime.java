package enums;

public enum MeanTime {
    TimeT("тем временем"), TimeM("понинутно"), Seem("кажутся"), Finally("И вот наконец");

    private final String name;

    MeanTime(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
