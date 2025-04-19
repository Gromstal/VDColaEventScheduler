package org.example.vdcolataskscheduler.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.example.vdcolataskscheduler.entity.Task;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC, force = true)
public class UserDto {

    private Long id;

    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Логин может содержать только латинские буквы и цифры")
    @NotBlank(message = "Логин обязателен")
    @Size(min = 5, max = 12, message = "Логин должен содержать от 5 до 12 символов")
    private String login;

    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Пароль может содержать только латинские буквы и цифры")
    @NotBlank(message = "Пароль обязателен")
    @Size(min = 5, max = 12, message = "Пароль должен содержать от 5 до 12 символов")
    private String password;

    private List<Task> taskList = new ArrayList<>();

}
