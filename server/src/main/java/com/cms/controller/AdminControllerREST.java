package com.cms.controller;

import com.cms.controller.annotations.AdminEndpoint;
import com.cms.controller.dto.user.admin.AdminResourceResponseDTO;
import com.cms.controller.dto.user.admin.ApiKeyResponseDTO;
import com.cms.controller.dto.testimonial.TestimonialResponseDTO;
import com.cms.controller.dto.user.UserRequestSimpleDTO;
import com.cms.controller.dto.user.UserResponseSimpleDTO;
import com.cms.controller.dto.user.editor.EditorResponseSimpleDTO;
import com.cms.controller.dto.utils.table.TableResponseDTO;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.user.impl.Editor;
import com.cms.model.user.impl.admin.AdminResource;
import com.cms.model.user.impl.admin.ApiKey;
import com.cms.services.AdminService;
import com.cms.services.TestimonialService;
import com.cms.utils.AuthUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("/api-key")
    @AdminEndpoint
    @Operation(
            summary = "Obtener API Key del administrador",
            description = "Retorna la API Key asociada al admin."
    )
    @ApiResponse(responseCode = "200", description = "API Key obtenida exitosamente")
    public ResponseEntity<ApiKeyResponseDTO> getApiKey(@RequestAttribute("userId") Long idAdmin) {
        ApiKey key = adminService.getApiKey(idAdmin);
        return ResponseEntity.ok(ApiKeyResponseDTO.fromModel(key));
    }

    @PostMapping
    @AdminEndpoint
    @Operation(
            summary = "Crear un editor",
            description = "Retorna al editor generado por el admin."
    )
    @ApiResponse(responseCode = "200", description = "Editor generado exitosamente")
    public ResponseEntity<UserResponseSimpleDTO> createEditor(
            @RequestBody UserRequestSimpleDTO request,
            @RequestAttribute("userId") Long idAdmin) {
        Editor editor = adminService.createEditor(request.toModelEditor(), idAdmin);

        return  ResponseEntity.ok(UserResponseSimpleDTO.fromModel(editor));
    }

    @GetMapping("/users")
    @AdminEndpoint
    @Operation(
            summary = "Listar editores del admin",
            description = "Retorna una tabla paginada con todos los editores creados por el admin autenticado."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Listado obtenido exitosamente",
            content = @Content(
                    schema = @Schema(implementation = TableResponseDTO.class),
                    examples = @ExampleObject(value = """
                        {
                          "headers": ["NOMBRE DE USUARIO", "MAIL", "ROL", "NºTESTIMONIOS", "ESTADO"],
                          "content": [
                            {
                              "id": 1,
                              "fullName": "Tomas Kumar",
                              "email": "tm@gmail.com",
                              "role": "EDITOR",
                              "testimonialCount": 3,
                              "enabled": true
                            }
                          ],
                          "page": 0,
                          "size": 5,
                          "totalElements": 1,
                          "totalPages": 1
                        }
                        """)
            )
    )
    public ResponseEntity<TableResponseDTO<EditorResponseSimpleDTO>> getAllUsers(
            @RequestAttribute("userId") Long idAdmin,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Page<Editor> editors = adminService.findAllEditors(idAdmin, page, size);

        Page<EditorResponseSimpleDTO> dtos = editors.map(EditorResponseSimpleDTO::fromModel);

        return ResponseEntity.ok(
                TableResponseDTO.fromPage(
                        List.of("NOMBRE DE USUARIO", "MAIL", "ROL", "NºTESTIMONIOS", "ESTADO"),
                        dtos,
                        EditorResponseSimpleDTO::id
                )
        );
    }

}
