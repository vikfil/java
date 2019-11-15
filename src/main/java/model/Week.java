package model;

public enum Week {
    MONDAY("monday"), TUESDAY("tuesday"), WEDNESDAY("wednesday"),
    THURSDAY("thursday"), FRIDAY("friday"), SATURDAY("saturday"), SUNDAY("sunday");

    private final String value;
    private Week(String value) {
        this.value = value;
    }

    public final String getValue() {
        return value;
    }

}
