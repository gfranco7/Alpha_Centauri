package com.campus.alphacentauri.usuario.infrastructure;

import com.campus.alphacentauri.usuario.application.UserServiceImpl;
import com.campus.alphacentauri.usuario.domain.RegisterUserDTO;
import com.campus.alphacentauri.usuario.domain.User;
import com.campus.alphacentauri.usuario.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/api/register")
    public ResponseEntity<?> register(@RequestBody RegisterUserDTO registerUserDTO) {
        try {
            if (userRepository.existsByUsername(registerUserDTO.getUsername())) {
                throw new IllegalArgumentException("Username already exists");
            }
            if (userRepository.existsByEmail(registerUserDTO.getEmail())) {
                throw new IllegalArgumentException("Email already exists");
            }

            User savedUser = userService.register(registerUserDTO);

            return ResponseEntity.ok("User registrado exitosamente: " + savedUser.getEmail());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al registrar el User.");
        }
    }
}