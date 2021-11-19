package com.faculdade.MyJourney.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @GetMapping("/blank")
    public String blank() {
        return "blank";
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }

    @GetMapping("/construcao")
    public String construcao() {
        return "construcao";
    }

}
