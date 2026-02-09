package ru.nikita.spring.boot.security.pp_3_1_2.controllers;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.nikita.spring.boot.security.pp_3_1_2.model.User;
import ru.nikita.spring.boot.security.pp_3_1_2.security.PersonDetails;


@Controller
public class UserController {

    @GetMapping("/user")
    public String showUserInfo(Model model, Authentication authentication){

        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        User user = personDetails.getUser();

        model.addAttribute("user", user);

        return "user";
    }

}
