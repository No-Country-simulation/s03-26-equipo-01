package com.cms.controller;

import com.cms.controller.annotations.AdminEndpoint;
import com.cms.controller.dto.admin.AdminResourceResponseDTO;
import com.cms.model.user.impl.admin.AdminResource;
import com.cms.services.AdminService;
import com.cms.utils.AuthUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/admin")
@Tag(name = "Admin", description = "Operaciones relacionadas con la gestión del administrador")
public class AdminControllerREST {

    private final AuthUtils  authUtils;
    private final AdminService adminService;

    public AdminControllerREST(AuthUtils authUtils, AdminService adminService) {
        this.authUtils = authUtils;
        this.adminService = adminService;
    }


    @GetMapping("/recursos")
    @AdminEndpoint
    @Operation(
            summary = "Obtener recursos del administrador",
            description = "Retorna los recursos necesarios para el panel del administrador: categorías, editores, tags y estados de testimonio."
    )
    @ApiResponse(responseCode = "200", description = "Recursos obtenidos exitosamente")
    @ApiResponse(responseCode = "404", description = "Administrador no encontrado")
    public ResponseEntity<AdminResourceResponseDTO> getAdminResource(Authentication auth){

        Long idAdmin = authUtils.getUserId(auth);

        AdminResource adminResource = adminService.getResource(idAdmin);

        AdminResourceResponseDTO adminResourceResponseDTO = AdminResourceResponseDTO.fromModel(adminResource);

        return ResponseEntity.ok(adminResourceResponseDTO);
    }
}
