package org.example.vdcolataskscheduler.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class StartingController {

    @GetMapping()
    public String getDefaultPage() {
        return "startingPage";
    }
}