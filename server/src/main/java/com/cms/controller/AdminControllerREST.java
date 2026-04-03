package com.cms.controller;

import com.cms.controller.dto.admin.AdminResourceResponseDTO;
import com.cms.model.user.impl.admin.AdminResource;
import com.cms.services.AdminService;
import com.cms.utils.AuthUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/admin")
public class AdminControllerREST {

    private final AuthUtils  authUtils;
    private final AdminService adminService;

    public AdminControllerREST(AuthUtils authUtils, AdminService adminService) {
        this.authUtils = authUtils;
        this.adminService = adminService;
    }


    @GetMapping("/recursos")
    public ResponseEntity<AdminResourceResponseDTO> getAdminResource(Authentication auth){

        Long idAdmin = authUtils.getUserId(auth);

        AdminResource adminResource = adminService.getResource(idAdmin);

        AdminResourceResponseDTO adminResourceResponseDTO = AdminResourceResponseDTO.fromModel(adminResource);

        return ResponseEntity.ok(adminResourceResponseDTO);
    }
}
