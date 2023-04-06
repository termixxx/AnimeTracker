package org.example.entities.enums;

public enum Condition {
    WATCHING("Смотрю"),
    PLANNED("Планирую"),
    WATCHED("Посмотрел(а)");
    private final String str;

    Condition(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
