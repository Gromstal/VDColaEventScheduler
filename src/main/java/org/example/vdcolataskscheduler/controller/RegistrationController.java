package org.example.vdcolataskscheduler.controller;

import lombok.RequiredArgsConstructor;
import org.example.vdcolataskscheduler.dto.UserDto;
import org.example.vdcolataskscheduler.service.RegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/regist")
public class RegistrationController {

    private final RegistrationService registrationService;

    @GetMapping
    public String getRegistrationPage() {
        return "registrationPage";
    }

    @PostMapping
    public String postRegistration(@ModelAttribute UserDto userDto, Model model) {
        return registrationService.postRegistration(userDto, model);
    }

}
