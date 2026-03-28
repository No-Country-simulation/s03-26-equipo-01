package com.cms.controller;

import com.cms.controller.dto.user.UserResponseSimpleDTO;
import com.cms.model.user.User;
import com.cms.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "Usuario", description = "Endpoints para la gestion de usuarios")
public class UserControllerREST {

    private final UserService userService;

    public UserControllerREST(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{enabled}")
    public ResponseEntity<List<UserResponseSimpleDTO>> getUsersByEnabled(@PathVariable boolean enabled) {
        List<User> users = userService.findAllEnabled(enabled);

        List<UserResponseSimpleDTO> userResponseSimpleDTOs = users.stream().map(UserResponseSimpleDTO::fromModel).toList();

        return ResponseEntity.ok(userResponseSimpleDTOs);
    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<?> deleteUser(@PathVariable Long idUser) {
        userService.disableUser(idUser);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/enable/{idUser}")
    public ResponseEntity<?> enableUser(@PathVariable Long idUser) {
        userService.enableUser(idUser);

        return ResponseEntity.noContent().build();
    }

}
