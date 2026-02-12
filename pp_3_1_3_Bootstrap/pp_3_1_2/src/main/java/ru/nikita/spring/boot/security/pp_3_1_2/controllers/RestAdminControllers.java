package ru.nikita.spring.boot.security.pp_3_1_2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.nikita.spring.boot.security.pp_3_1_2.DTO.UserDTO;
import ru.nikita.spring.boot.security.pp_3_1_2.model.User;
import ru.nikita.spring.boot.security.pp_3_1_2.security.PersonDetails;
import ru.nikita.spring.boot.security.pp_3_1_2.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/admin/api")
public class RestAdminControllers {

    private final UserService userService;

    @Autowired
    public RestAdminControllers(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserByID(@PathVariable int id){
        return userService.findById(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<User> addNewUser(@RequestBody UserDTO userDTO){
        User savedUser = userService.saveUser(userDTO.getUser(), userDTO.getRoleNames());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me")
    public ResponseEntity<User> showAdminInfo(Authentication authentication){

        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        User user = personDetails.getUser();

        return ResponseEntity.ok(user);
    }

}
