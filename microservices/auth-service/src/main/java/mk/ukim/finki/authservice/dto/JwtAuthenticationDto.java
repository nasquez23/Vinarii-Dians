package mk.ukim.finki.authservice.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class JwtAuthenticationDto {
    private String token;
    private String refreshToken;
    private Long userId;
    private String userEmail;
    private String firstName;
}
