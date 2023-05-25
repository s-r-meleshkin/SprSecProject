package com.example.springsecurityapplication.controllers;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthenticationController {


    @GetMapping("/authentication")
    public String login(){

        return "authentication";
    }

    @PostMapping("/logout")
    public String logout() {

        return "authentication";
    }

}
