package mk.ukim.finki.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.authservice.model.User;
import mk.ukim.finki.authservice.repository.UserRepo;
import mk.ukim.finki.authservice.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepository;

    // Добивање детали за корисник според неговото корисничко име
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

    // Добивање на корисник според ID
    public User findById(Long Id) {
        return userRepository.findById(Id).get();
    }
}