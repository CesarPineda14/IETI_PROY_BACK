package org.escuelaing.eci.Controller;

import org.escuelaing.eci.repository.user.User;
import org.escuelaing.eci.repository.user.UserMongoRepository;
import org.escuelaing.eci.Security.JwtUtil;
import org.escuelaing.eci.response.LoginResponse;
import org.escuelaing.eci.service.user.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")

public class AuthController {
    private final UsersService userService;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserMongoRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;


    public AuthController(UsersService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        User user = userRepository.findByname(username);
        System.out.println("Attempting to find user: " + username);
        System.out.println(" user: " + user);
        if (user != null && password.equals( user.getPasswordHash())) {
            String token = JwtUtil.generateToken(username);
            return ResponseEntity.ok(new LoginResponse(token));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Credenciales inválidas");
    }



    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        System.out.println("Received request to register user: " + user);
        return this.userService.save(user);
    }


}
