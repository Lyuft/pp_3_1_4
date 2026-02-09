package ru.nikita.spring.boot.security.pp_3_1_2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.nikita.spring.boot.security.pp_3_1_2.model.User;
import ru.nikita.spring.boot.security.pp_3_1_2.security.PersonDetails;
import ru.nikita.spring.boot.security.pp_3_1_2.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "")
    public String showAllUsers(Model model, Authentication authentication){

        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        User user = personDetails.getUser();
        model.addAttribute("userInfo", user);

        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);


        model.addAttribute("user", new User());


        model.addAttribute("tempUser", new User());

        return "admin/all-users";
    }

    @PostMapping(value = "/saveUser")
    public String saveUser(@ModelAttribute("user") User user, @RequestParam("role") List<String> roleName){

        userService.saveUser(user,roleName);

        return "redirect:/admin";
    }

    @PostMapping(value = "/deleteUser")
    public String deleteUser(@RequestParam("id") int id){

        userService.deleteUser(id);

        return "redirect:/admin";
    }
}
