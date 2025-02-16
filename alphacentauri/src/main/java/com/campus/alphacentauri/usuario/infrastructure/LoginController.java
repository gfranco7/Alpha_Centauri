package com.campus.alphacentauri.usuario.infrastructure;


import com.campus.alphacentauri.security.JWTAuthtenticationConfig;
import com.campus.alphacentauri.usuario.application.UserServiceImpl;
import com.campus.alphacentauri.usuario.domain.LoginResponseDTO;
import com.campus.alphacentauri.usuario.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class LoginController {

    @Autowired
    private JWTAuthtenticationConfig jwtAuthtenticationConfig;

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/api/login")
    public ResponseEntity<?> login(
            @RequestParam("email") String email,
            @RequestParam("password") String password) {

        if (userService.verifyByEmail(email, password)) {
            String token = jwtAuthtenticationConfig.getJWTToken(email);

            User user = userService.findByEmail(email);

            LoginResponseDTO responseDTO = new LoginResponseDTO();
            responseDTO.setToken(token);
            responseDTO.setId(user.getId());
            responseDTO.setName(user.getName());
            responseDTO.setUsername(user.getUsername());
            responseDTO.setPhoto(user.getPhoto());
            responseDTO.setEmail(user.getEmail());
            responseDTO.setLastname(user.getLastname());
            responseDTO.setBio(user.getBio());

            List<Long> publications = user.getPublications().stream()
                    .map(publication -> publication.getId())
                    .collect(Collectors.toList());

            List<Long> followersIds = user.getFollowers().stream()
                    .map(follow -> follow.getFollowing().getId())
                    .collect(Collectors.toList());

            List<Long> followingIds = user.getFollowing().stream()
                    .map(follow -> follow.getFollower().getId())
                    .collect(Collectors.toList());

            responseDTO.setFollowersIds(followersIds);
            responseDTO.setFollowingIds(followingIds);
            responseDTO.setPublications(publications);

            return ResponseEntity.ok(responseDTO);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}