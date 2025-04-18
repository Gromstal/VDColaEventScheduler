package org.example.vdcolataskscheduler.dto;

import lombok.*;
import org.example.vdcolataskscheduler.entity.Task;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
public class UserDto {

    private Long id;
    private String login;
    private String password;
    private List<Task> taskList = new ArrayList<>();

}
