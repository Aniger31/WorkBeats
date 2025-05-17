package com.example.workbeats.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorPagina {
    @GetMapping("/")
    public String login() {
        return "redirect:/login.html";
    }


}
