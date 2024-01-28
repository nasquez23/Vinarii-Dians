package mk.ukim.finki.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.authservice.dto.JwtAuthenticationDto;
import mk.ukim.finki.authservice.dto.RefreshTokenRequest;
import mk.ukim.finki.authservice.dto.SignInRequest;
import mk.ukim.finki.authservice.dto.SignUpRequest;
import mk.ukim.finki.authservice.model.Role;
import mk.ukim.finki.authservice.model.User;
import mk.ukim.finki.authservice.repository.UserRepo;
import mk.ukim.finki.authservice.service.AuthenticationService;
import mk.ukim.finki.authservice.service.JWTService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    // Метод за регистрација на корисник
    public User signUp(SignUpRequest signUpRequest){
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setFirstName(signUpRequest.getFirstName());
        System.out.println("Sign up req:" + signUpRequest);
        user.setRoles(Role.USER);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        return userRepository.save(user);
    }

    // Метод за најава на корисник и генерирање на JWT токен
    public JwtAuthenticationDto login(SignInRequest sign){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(sign.getEmail(), sign.getPassword()));
        var user = userRepository.findByEmail(sign.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid Email or password"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
        JwtAuthenticationDto jwtAuthenticationDto = new JwtAuthenticationDto();
        jwtAuthenticationDto.setToken(jwt);
        jwtAuthenticationDto.setRefreshToken(jwt);
        
        return jwtAuthenticationDto;
    }

    // Метод за освежување на ЈWT токен
    public JwtAuthenticationDto refreshToken(RefreshTokenRequest refreshTokenRequest){
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        if(jwtService.isTokenValid(refreshTokenRequest.getToken(), user)){
            var jwt = jwtService.generateToken(user);
            JwtAuthenticationDto jwtAuthenticationDto = new JwtAuthenticationDto();
            jwtAuthenticationDto.setToken(jwt);
            jwtAuthenticationDto.setRefreshToken(refreshTokenRequest.getToken());

            return jwtAuthenticationDto;
        }
        return null;
    }
}
