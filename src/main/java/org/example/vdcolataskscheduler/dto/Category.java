package org.example.vdcolataskscheduler.dto;

import lombok.Getter;
import java.io.Serializable;

@Getter
public enum Category implements Serializable {
    DOCTOR("Поход к доктору"),
    FRIEND("Встреча с другом"),
    BEAUTY("Бьюти"),
    EVENT("Другое событие");

    private final String description;

    Category(String description) {
        this.description = description;
    }
}