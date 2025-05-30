package in.jaysan.controller;

import in.jaysan.dto.AuthenticationRequest;
import in.jaysan.service.AppUserDetailsService;
import in.jaysan.util.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AppUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;


    @PostMapping("/login")
    public void login(@RequestBody AuthenticationRequest request, HttpServletResponse response,HttpServletRequest req)
    {
        authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        final UserDetails userDetails=userDetailsService.loadUserByUsername(request.getEmail());
        final String jwtToken= jwtUtil.generateToken(userDetails);



        Cookie cookie = new jakarta.servlet.http.Cookie("jwt", jwtToken);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");

        cookie.setMaxAge(24 * 60 * 60);

        response.addCookie(cookie);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @PostMapping("/logout")
    public void logout(HttpServletResponse response) {

        Cookie cookie = new Cookie("jwt", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(0); // Expire the cookie immediately

        response.addCookie(cookie);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
