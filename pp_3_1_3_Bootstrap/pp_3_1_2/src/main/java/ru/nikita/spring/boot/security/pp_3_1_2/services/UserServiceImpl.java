package ru.nikita.spring.boot.security.pp_3_1_2.services;

import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nikita.spring.boot.security.pp_3_1_2.DAO.RoleRepository;
import ru.nikita.spring.boot.security.pp_3_1_2.DAO.UserRepository;
import ru.nikita.spring.boot.security.pp_3_1_2.model.Role;
import ru.nikita.spring.boot.security.pp_3_1_2.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    @Transactional
    public void saveUser(User user, List<String> roleNames) {
        if(user.getId() > 0 && StringUtils.isBlank(user.getPassword())){
            User existingUser = userRepository.findById(user.getId()).orElseThrow(()-> new RuntimeException("Пользователь не найден"));
            user.setPassword(existingUser.getPassword());
        }else if (StringUtils.isNotBlank(user.getPassword())){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        user.getRoles().clear();

        if (roleNames !=null) {
            for (String nameRole : roleNames) {
                Role role = roleRepository.findByRole(nameRole).orElseThrow(() -> new RuntimeException("Роль с именем " + nameRole + " не найдена!!"));
                user.getRoles().add(role);
            }
        }

        userRepository.save(user);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userRepository.findAllWithRoles();
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

}
