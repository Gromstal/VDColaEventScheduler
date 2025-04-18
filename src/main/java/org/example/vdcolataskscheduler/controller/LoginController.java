package org.example.vdcolataskscheduler.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping()
    public String getLoginPage(@RequestParam(value = "error", required = false) String error,
                               Model model,
                               Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            logger.info("Authentication is successful");

            return "redirect:/mainPage";
        }

        if (error != null) {
            model.addAttribute("error", "Неверный логин или пароль");
        }
        return "loginPage";
    }
}
