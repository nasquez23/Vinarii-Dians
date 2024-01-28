package mk.ukim.finki.authservice.controller;


import lombok.RequiredArgsConstructor;
import mk.ukim.finki.authservice.dto.PublicUserInfoDto;
import mk.ukim.finki.authservice.model.User;
import mk.ukim.finki.authservice.service.UserService;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public PublicUserInfoDto getUserData(@PathVariable Long id){

        User user = userService.findById(id);
        return new PublicUserInfoDto(id, user.getFirstName(), user.getEmail());
    }
}
