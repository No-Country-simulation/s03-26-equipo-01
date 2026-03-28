package com.cms.controller;

import com.cms.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(name = "Usuario", description = "Endpoints para la gestion de usuarios")
public class UserControllerREST {

    private final UserService userService;

    public UserControllerREST(UserService userService) {
        this.userService = userService;
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
