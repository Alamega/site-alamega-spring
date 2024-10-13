package com.alamega.backend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainRestController {
    @GetMapping("/checkHealth")
    public String checkHealth() {
        return "OK";
    }
}
