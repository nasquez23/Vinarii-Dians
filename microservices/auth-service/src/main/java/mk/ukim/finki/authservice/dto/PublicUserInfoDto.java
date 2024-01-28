package mk.ukim.finki.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PublicUserInfoDto {
    private Long id;
    private String name;
    private String email;
}