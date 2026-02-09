package ru.nikita.spring.boot.security.pp_3_1_2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.nikita.spring.boot.security.pp_3_1_2.DAO.UserRepository;
import ru.nikita.spring.boot.security.pp_3_1_2.model.User;
import ru.nikita.spring.boot.security.pp_3_1_2.security.PersonDetails;

import java.util.Optional;

@Service
public class UserDetailsServices implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsernameWithRoles(username);

        if (user.isEmpty()){
            throw new UsernameNotFoundException("User not found!");
        }

        return new PersonDetails(user.get());
    }
}
