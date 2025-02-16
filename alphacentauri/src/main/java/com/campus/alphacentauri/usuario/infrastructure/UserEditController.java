package com.campus.alphacentauri.usuario.infrastructure;


import com.campus.alphacentauri.usuario.application.UserServiceImpl;
import com.campus.alphacentauri.usuario.domain.UserEditDTO;
import com.campus.alphacentauri.usuario.domain.UserProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/edit")
public class UserEditController {

    private final UserServiceImpl userService;

    @Autowired
    public UserEditController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PutMapping("/{userId}")
    public UserEditDTO updateUserProfile(@PathVariable Long userId, @RequestBody UserEditDTO userEditDTO) {
        userEditDTO.setId(userId);
        return userService.saveEditProf(userEditDTO);
    }

}
