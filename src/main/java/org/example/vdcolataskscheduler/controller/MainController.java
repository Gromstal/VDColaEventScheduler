package org.example.vdcolataskscheduler.controller;

import lombok.RequiredArgsConstructor;
import org.example.vdcolataskscheduler.dto.TaskDto;
import org.example.vdcolataskscheduler.service.TaskService;
import org.example.vdcolataskscheduler.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("/mainPage")
public class MainController {

    private final TaskService taskService;
    private final UserService userService;


    @GetMapping
    public String getMainPage(Model model) {
        model.addAttribute("taskList", taskService.findAllTasks());
        return "mainPage";
    }

    @GetMapping("/edit")
    public String getEditPage(@RequestParam("id") Long id, Model model) {
        TaskDto selectedTask = taskService.findTaskById(id);
        model.addAttribute("taskList", taskService.findAllTasks());
        model.addAttribute("taskDto", selectedTask);

        return "mainPage";
    }

    @PostMapping
    public String addTask(TaskDto taskDto, Model model) {
        taskService.addTask(taskDto);

        model.addAttribute("taskList", taskService.findAllTasks());
        return "redirect:/mainPage";
    }

    @PostMapping("/edit")
    public String updateTask(@ModelAttribute TaskDto taskDto, Model model) {
        taskService.updateTask(taskDto);

        model.addAttribute("taskList", taskService.findAllTasks());
        return "redirect:/mainPage";
    }
}
