package org.example.vdcolataskscheduler.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.vdcolataskscheduler.dto.UserDto;
import org.example.vdcolataskscheduler.service.RegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/regist")
public class RegistrationController {

    private final RegistrationService registrationService;

    @GetMapping
    public String getRegistrationPage(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "registrationPage";
    }

    @PostMapping
    public String postRegistration(
            @ModelAttribute("userDto") @Valid UserDto userDto,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "registrationPage";
        }

        return registrationService.postRegistration(userDto, model);
    }

}
