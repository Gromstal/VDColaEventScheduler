package org.example.vdcolataskscheduler.service;

import lombok.RequiredArgsConstructor;
import org.example.vdcolataskscheduler.dto.UserDto;
import org.example.vdcolataskscheduler.entity.User;
import org.example.vdcolataskscheduler.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String postRegistration(UserDto userDto, Model model) {

        if (userRepository.existsByLogin(userDto.getLogin())) {
            model.addAttribute("loginHasExists", "Пользователь с таким логином уже существует");
            return "registrationPage";
        }
        User newUser = new User(userDto.getLogin(), passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(newUser);
        return "redirect:/mainPage";
    }
}
