package com.cms.controller;

import com.cms.controller.annotations.AdminEndpoint;
import com.cms.controller.dto.admin.AdminResourceResponseDTO;
import com.cms.controller.dto.testimonial.TestimonialResponseDTO;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.user.impl.admin.AdminResource;
import com.cms.services.AdminService;
import com.cms.services.TestimonialService;
import com.cms.utils.AuthUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/admin")
@Tag(name = "Admin", description = "Operaciones relacionadas con la gestión del administrador")
public class AdminControllerREST {

    private final AuthUtils  authUtils;
    private final AdminService adminService;
    private final TestimonialService testimonialService;

    public AdminControllerREST(AuthUtils authUtils, AdminService adminService, TestimonialService testimonialService) {
        this.authUtils = authUtils;
        this.adminService = adminService;
        this.testimonialService = testimonialService;
    }
    @GetMapping("/testimonial")
    @AdminEndpoint
    @Operation(
            summary = "Obtener testimonios del admin",
            description = "Retorna todos los testimonios asociados a los embeds del admin autenticado"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Lista de testimonios obtenida exitosamente",
            content = @Content(
                    schema = @Schema(implementation = TestimonialResponseDTO.class)
            )
    )
    public ResponseEntity<List<TestimonialResponseDTO>> getAllTestimonials(Authentication authentication) {
        Long idAdmin = authUtils.getUserId(authentication);

        List<Testimonial> testimonials = testimonialService.findTestimonialByAdmin(idAdmin);

        List<TestimonialResponseDTO> response = testimonials.stream().map(TestimonialResponseDTO::fromModel).toList();

        return ResponseEntity.ok(response);
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

    @PatchMapping("/testimonials/{idTestimonial}/advance")
    @AdminEndpoint
    public ResponseEntity<TestimonialResponseDTO> advance(@PathVariable Long idTestimonial) {

        TestimonialResponseDTO response = TestimonialResponseDTO.fromModel(testimonialService.advanceByAdmin(idTestimonial));

        return ResponseEntity.ok(response);
    }
}
