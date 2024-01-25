package mk.ukim.finki.diansvinarii.repository;

import mk.ukim.finki.diansvinarii.model.Role;
import mk.ukim.finki.diansvinarii.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email); // Метод за добивање на корисник според емаил
    User findByRoles(Role role); // Метод за добивање на корисник според улога
}