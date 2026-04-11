package com.cms.controller;
import com.cms.controller.annotations.AdminEndpoint;
import com.cms.controller.annotations.EditorEndpoint;
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
@RequestMapping("/editor")
@Tag(name = "Editor", description = "Operaciones relacionadas con la gestión del editor")
public class EditorControllerREST {

    private final AuthUtils  authUtils;
    private final TestimonialService testimonialService;

    public EditorControllerREST(AuthUtils authUtils, TestimonialService testimonialService) {
        this.authUtils = authUtils;
        this.testimonialService = testimonialService;
    }


    @PatchMapping("/testimonials/{idTestimonial}/advance")
    @EditorEndpoint
    @Operation(
            summary = "Avanzar testimonio de Borrador a Pendiente",
            description = "Permite al editor enviar un testimonio en estado Borrador para revisión, cambiando su estado a Pendiente."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Testimonio avanzado a Pendiente correctamente",
                    content = @Content(schema = @Schema(implementation = TestimonialResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "El testimonio no está en estado Borrador",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Testimonio no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Acceso denegado",
                    content = @Content)
    })
    public ResponseEntity<TestimonialResponseDTO> advance(@PathVariable Long idTestimonial) {
        TestimonialResponseDTO response = TestimonialResponseDTO.fromModel(testimonialService.advanceByEditor(idTestimonial));
        return ResponseEntity.ok(response);
    }
}
