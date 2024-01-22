package enums;

public enum MeanTime {
    TIME_T("тем временем"), TIME_M("понинутно"), SEEM("кажутся"), FINALLY("И вот наконец");

    private final String name;

    MeanTime(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
