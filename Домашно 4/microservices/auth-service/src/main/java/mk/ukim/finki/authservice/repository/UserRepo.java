package mk.ukim.finki.authservice.repository;


import mk.ukim.finki.authservice.model.Role;
import mk.ukim.finki.authservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email); // Метод за добивање на корисник според емаил
    User findByRoles(Role role); // Метод за добивање на корисник според улога
}