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
public class
AdminControllerREST {

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

    @DeleteMapping("/testimonial/{id}")
    @AdminEndpoint
    @Operation(
            summary = "Eliminar testimonio",
            description = "Elimina un testimonio por su ID. Solo se pueden eliminar testimonios en estados APPROVED o PUBLISHED."
    )
    @ApiResponse(responseCode = "204", description = "Testimonio eliminado exitosamente")
    @ApiResponse(responseCode = "404", description = "Testimonio no encontrado")
    @ApiResponse(responseCode = "409", description = "No se puede eliminar testimonio en estado DRAFT o PENDING")
    public ResponseEntity<Void> deleteTestimonial(
            @PathVariable Long id,
            Authentication authentication
    ) {
        Long idAdmin = authUtils.getUserId(authentication);

        Testimonial testimonial = testimonialService.findTestimonialById(id);
        if (testimonial == null) {
            return ResponseEntity.notFound().build();
        }
        List<Testimonial> adminTestimonials = testimonialService.findTestimonialByAdmin(idAdmin);
        boolean belongsToAdmin = adminTestimonials.stream()
                .anyMatch(t -> t.getId().equals(id));

        if (!belongsToAdmin) {
            return ResponseEntity.status(403).build();
        }


        testimonialService.deleteTestimonial(id);
        return ResponseEntity.noContent().build();
}
}
