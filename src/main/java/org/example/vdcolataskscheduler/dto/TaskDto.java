package org.example.vdcolataskscheduler.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
public class TaskDto implements Serializable {

    private Long id;
    private Long userId;
    private Category category;
    private String description;
    private LocalDate date;
    private boolean isNotified;
    private LocalDateTime createdAt;

}
