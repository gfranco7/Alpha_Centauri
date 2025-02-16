package com.campus.alphacentauri.usuario.infrastructure;

import com.campus.alphacentauri.usuario.application.UserService;
import com.campus.alphacentauri.usuario.application.UserService;
import com.campus.alphacentauri.usuario.application.UserServiceImpl;
import com.campus.alphacentauri.usuario.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImpl userService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserServiceImpl userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/search")
    public List<UserSearchDTO> searchUsers(@RequestParam String query) {
        List<User> users = userService.searchUsers(query);
        return users.stream()
                .map(user -> new UserSearchDTO(
                        user.getId(),
                        user.getName(),
                        user.getUsername(),
                        user.getPhoto()
                ))
                .collect(Collectors.toList());
    }

    @PatchMapping(value = "/{userId}/photo", consumes = "multipart/form-data")
    public ResponseEntity<?> updateUserPhoto(@PathVariable Long userId,
                                             @RequestParam("photo") MultipartFile photo) {
        try {
            String uploadDir = "uploads/";
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileName = System.currentTimeMillis() + "_" + photo.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);

            Files.copy(photo.getInputStream(), filePath);

            Optional<User> optionalUser = userRepository.findById(userId);
            if (!optionalUser.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
            }
            User user = optionalUser.get();
            user.setPhoto(fileName);
            userRepository.save(user);

            Map<String, Object> response = new HashMap<>();
            response.put("id", user.getId());
            response.put("photo", user.getPhoto());

            return ResponseEntity.ok(response);
        } catch (IOException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al subir la imagen");
        }
    }

    @PutMapping("/edit/{userId}")
    public LoginResponseDTO editUser(@PathVariable long userId, @RequestBody LoginResponseDTO loginResponseDTO) {
        User updatedUser = userService.edit(userId, loginResponseDTO);
        LoginResponseDTO response = new LoginResponseDTO();
        response.setId(updatedUser.getId());
        response.setName(updatedUser.getName());
        response.setLastname(updatedUser.getLastname());
        response.setEmail(updatedUser.getEmail());
        response.setUsername(updatedUser.getUsername());
        response.setBio(updatedUser.getBio());
        return response;
    }

    @GetMapping("/followings/{userId}")
    public UserProfileDTO getFollowingCard(@PathVariable long userId) {
        User user = userService.getUserProfile(userId);

        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setId(user.getId());
        userProfileDTO.setName(user.getName());
        userProfileDTO.setLastname(user.getLastname());
        userProfileDTO.setUsername(user.getUsername());
        userProfileDTO.setPhoto(user.getPhoto());
        return userProfileDTO;
    }

    @GetMapping("/{userId}")
    public UserProfileDTO getUserProfile(@PathVariable Long userId) {
        User user = userService.getUserProfile(userId);

        List<Long> publications = user.getPublications().stream()
                .map(publication -> publication.getId())
                .collect(Collectors.toList());

        List<Long> followersIds = user.getFollowers().stream()
                .map(follow -> follow.getFollower().getId())
                .collect(Collectors.toList());

        List<Long> followingIds = user.getFollowing().stream()
                .map(follow -> follow.getFollowing().getId())
                .collect(Collectors.toList());

        return new UserProfileDTO(
                user.getId(),
                user.getName(),
                user.getLastname(),
                user.getUsername(),
                user.getPhoto(),
                user.getBio(),
                publications,
                followersIds,
                followingIds
        );
    }
}
