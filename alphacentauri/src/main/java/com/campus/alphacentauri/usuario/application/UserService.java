package com.campus.alphacentauri.usuario.application;


import com.campus.alphacentauri.usuario.domain.LoginResponseDTO;
import com.campus.alphacentauri.usuario.domain.RegisterUserDTO;
import com.campus.alphacentauri.usuario.domain.User;
import java.util.List;

public interface UserService {
    List<User> getAllUser();
    User getUserById(Long id);
    User findByUsername(Long id);
    User saveUser(User user);
    void deleteUser(Long id);
    boolean emailExists(String email);
    User findByEmail(String email);
    User registrar(RegisterUserDTO registerUserDTO);
    List<User> searchUsers(String query); // Buscar usuarios
    User getUserProfile(Long userId);
    User editUser(long userId, LoginResponseDTO loginResponseDTO);
}

