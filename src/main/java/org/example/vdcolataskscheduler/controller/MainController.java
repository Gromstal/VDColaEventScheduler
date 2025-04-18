package org.example.vdcolataskscheduler.controller;

import lombok.RequiredArgsConstructor;
import org.example.vdcolataskscheduler.dto.TaskDto;
import org.example.vdcolataskscheduler.service.TaskServise;
import org.example.vdcolataskscheduler.service.UserServise;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/mainPage")
public class MainController {

    private final TaskServise taskServise;
    private final UserServise userServise;


    @GetMapping
    public String getmainPage(Model model) {
        model.addAttribute("taskList", taskServise.findAll(userServise.getCurrentUser()));
        return "mainPage";
    }

    @PostMapping
    public String addTask(TaskDto taskDto ,Model model) {
        taskServise.addTask(userServise.getCurrentUser(),taskDto);

        model.addAttribute("taskList", taskServise.findAll(userServise.getCurrentUser()));
        return "redirect:/mainPage";
    }
}
