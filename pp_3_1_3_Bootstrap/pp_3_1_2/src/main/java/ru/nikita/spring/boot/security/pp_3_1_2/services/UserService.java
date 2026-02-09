package ru.nikita.spring.boot.security.pp_3_1_2.services;

import ru.nikita.spring.boot.security.pp_3_1_2.model.User;

import java.util.List;


public interface UserService {

    void saveUser(User user, List<String> roleName);

    List<User> getAllUsers();

    void deleteUser(int id);


}
