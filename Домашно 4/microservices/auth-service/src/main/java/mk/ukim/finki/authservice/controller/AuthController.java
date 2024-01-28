package mk.ukim.finki.authservice.controller;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.authservice.dto.JwtAuthenticationDto;
import mk.ukim.finki.authservice.dto.RefreshTokenRequest;
import mk.ukim.finki.authservice.dto.SignInRequest;
import mk.ukim.finki.authservice.dto.SignUpRequest;
import mk.ukim.finki.authservice.model.User;
import mk.ukim.finki.authservice.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    // Регистрирање на корисник
    @PostMapping("/register")
    public ResponseEntity<User> signUp(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
    }

    // Најава на корисник
    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationDto> signIn(@RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authenticationService.login(signInRequest));
    }

    // Освежување на JWT токенот
    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationDto> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }
}
