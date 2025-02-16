package com.campus.alphacentauri.usuario.application;


import com.campus.alphacentauri.Seguimiento.domain.Seguimiento;
import com.campus.alphacentauri.Seguimiento.domain.SeguimientoRepository;
import com.campus.alphacentauri.Publicacion.domain.Publicacion;
import com.campus.alphacentauri.Publicacion.domain.PublicacionRepository;
import com.campus.alphacentauri.usuario.domain.LoginResponseDTO;
import com.campus.alphacentauri.usuario.domain.RegisterUserDTO;
import com.campus.alphacentauri.usuario.domain.User;
import com.campus.alphacentauri.usuario.domain.UserEditDTO;
import com.campus.alphacentauri.usuario.domain.UserProfileDTO;
import com.campus.alphacentauri.usuario.domain.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PublicacionRepository publicationRepo;

    @Autowired
    private SeguimientoRepository followRepository;

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

    public UserEditDTO saveEditProf(UserEditDTO userEditDTO) {
        User user = convertToEntity(userEditDTO);
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    public UserEditDTO convertToDTO(User user) {
        UserEditDTO dto = new UserEditDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setLastname(user.getLastname());
        dto.setUsername(user.getUsername());
        dto.setPhoto(user.getPhoto());
        dto.setBio(user.getBio());

        List<Long> publicationIds = user.getPublications().stream()
                .map(Publicacion::getId)
                .collect(Collectors.toList());
        dto.setPublications(publicationIds);

        List<Long> followerIds = user.getFollowers().stream()
                .map(follow -> follow.getFollower().getId())
                .collect(Collectors.toList());
        dto.setFollowersIds(followerIds);

        List<Long> followingIds = user.getFollowing().stream()
                .map(follow -> follow.getFollowing().getId())
                .collect(Collectors.toList());
        dto.setFollowingIds(followingIds);

        return dto;
    }

    public User convertToEntity(UserEditDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setLastname(dto.getLastname());
        user.setUsername(dto.getUsername());
        user.setPhoto(dto.getPhoto());
        user.setBio(dto.getBio());

        if (dto.getPublications() != null) {
            List<Publicacion> publications = dto.getPublications().stream()
                    .map(publicationRepo::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());
            user.setPublications(publications);
        }
        return user;
    }

}