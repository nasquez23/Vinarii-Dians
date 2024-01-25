package mk.ukim.finki.diansvinarii.configure;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import mk.ukim.finki.diansvinarii.service.JWTService;
import mk.ukim.finki.diansvinarii.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.thymeleaf.util.StringUtils;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String useremail;

        // Провери дали заглавјето е празно или не почнува со "Bearer "
        if(StringUtils.isEmpty(authHeader) || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response); // Ако условот е исполнет, продолжи со следните филтри
            return;
        }

        jwt = authHeader.substring(7);  // Извлекување JWT токен од заглавието "Authorization"
        useremail = jwtService.extractUserName(jwt);  // Извлекување корисничко име од JWT токенот

        // Провери дали корисничкото име не е празно и автентикацијата не постои во контекстот
        if(!StringUtils.isEmpty(useremail) && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userService.userDetailsService().loadUserByUsername(useremail);

            if(jwtService.isTokenValid(jwt, userDetails)){
                // Креирај нов контекст за сигурност и автентицирај го корисникот
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                securityContext.setAuthentication(token);
                SecurityContextHolder.setContext(securityContext);
            }
        }
        filterChain.doFilter(request,response); // Продолжи со следните филтри
    }
}
