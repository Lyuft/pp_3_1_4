package ru.nikita.spring.boot.security.pp_3_1_2.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.nikita.spring.boot.security.pp_3_1_2.model.User;
import ru.nikita.spring.boot.security.pp_3_1_2.security.PersonDetails;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping
    public String showAllUsers(){
        return "admin/all-users";
    }
}
