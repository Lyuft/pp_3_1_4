package ru.nikita.spring.boot.security.pp_3_1_2.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.nikita.spring.boot.security.pp_3_1_2.model.User;
import ru.nikita.spring.boot.security.pp_3_1_2.security.PersonDetails;


@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public String showUserInfo(){
        return "user";
    }

    @GetMapping("/api")
    @ResponseBody
    public ResponseEntity<User> showUserInfo(Authentication authentication){

        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        User user = personDetails.getUser();

        return ResponseEntity.ok(user);
    }

}
