package com.example.chapter01test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @RequestMapping("/question1")
    public String question1() {
        return "<h1>Welcome to the Schedule Management System</h1>" +
                "<a href='/question2/index'>Go to Schedule List</a>";
    }
}
