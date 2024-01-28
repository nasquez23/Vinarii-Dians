package mk.ukim.finki.authservice.service;


import mk.ukim.finki.authservice.dto.JwtAuthenticationDto;
import mk.ukim.finki.authservice.dto.RefreshTokenRequest;
import mk.ukim.finki.authservice.dto.SignInRequest;
import mk.ukim.finki.authservice.dto.SignUpRequest;
import mk.ukim.finki.authservice.model.User;

public interface AuthenticationService {
    User signUp(SignUpRequest signUpRequest);
    JwtAuthenticationDto login(SignInRequest sign);
    JwtAuthenticationDto refreshToken(RefreshTokenRequest refreshTokenRequest);
}
