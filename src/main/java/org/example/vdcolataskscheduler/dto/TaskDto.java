package org.example.vdcolataskscheduler.dto;

import lombok.*;
import org.example.vdcolataskscheduler.entity.User;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
public class TaskDto {

    private Long id;
    private User user;
    private Category category;
    private String description;
    private LocalDate date;
    private boolean isNotified;

}
