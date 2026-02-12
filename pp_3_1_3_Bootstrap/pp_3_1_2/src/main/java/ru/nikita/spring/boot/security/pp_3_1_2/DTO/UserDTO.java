package ru.nikita.spring.boot.security.pp_3_1_2.DTO;

import ru.nikita.spring.boot.security.pp_3_1_2.model.User;

import java.util.List;

public class UserDTO {

    private User user;

    private List<String> roleNames;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(List<String> roleNames) {
        this.roleNames = roleNames;
    }
}
