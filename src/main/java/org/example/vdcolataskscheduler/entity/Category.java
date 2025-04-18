package org.example.vdcolataskscheduler.entity;

public enum Category {
    DOCTOR("Поход к доктору"),
    FRIEND("Встреча с другом"),
    BEAUTY("Бьюти"),
    EVENT("Другое событие");

    private final String description;

    Category(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
