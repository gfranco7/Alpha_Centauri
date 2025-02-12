package com.campus.alphacentauri.usuario.application;

import com.campus.alphacentauri.usuario.domain.LoginResponseDTO;
import com.campus.alphacentauri.usuario.domain.RegisterUserDTO;
import com.campus.alphacentauri.usuario.domain.User;
import com.campus.alphacentauri.usuario.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User register(RegisterUserDTO registerUserDTO) {
        if (userRepository.existsByUsername(registerUserDTO.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (userRepository.existsByEmail(registerUserDTO.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        User User = new User();
        User.setName(registerUserDTO.getName());
        User.setLastname(registerUserDTO.getLastname());
        User.setEmail(registerUserDTO.getEmail());
        User.setUsername(registerUserDTO.getUsername());
        User.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
        User.setPhoto(registerUserDTO.getPhoto());

        return userRepository.save(User);
    }

    public User edit(long userId, LoginResponseDTO loginResponseDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        user.setName(loginResponseDTO.getName());
        user.setLastname(loginResponseDTO.getLastname());
        user.setEmail(loginResponseDTO.getEmail());
        user.setUsername(loginResponseDTO.getUsername());
        user.setBio(loginResponseDTO.getBio());

        return userRepository.save(user);
    }

    public boolean verifyByEmail(String email, String password) {
        User User = getUserByEmail(email);
        if (User == null) {
            return false;
        }
        return passwordEncoder.matches(password, User.getPassword());
    }

    public boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> searchUsers(String query) {
        return userRepository.findByNameContainingOrUsernameContaining(query, query);
    }

    public User getUserProfile(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}
