package ru.nikita.spring.boot.security.pp_3_1_2.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nikita.spring.boot.security.pp_3_1_2.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByRole(String role);
}
