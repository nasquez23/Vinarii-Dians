package mk.ukim.finki.authservice.service;

import mk.ukim.finki.authservice.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();
    
    public User findById(Long Id);
}
