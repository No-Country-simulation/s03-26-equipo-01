package com.cms.controller;
import com.cms.controller.annotations.EditorEndpoint;

import com.cms.controller.dto.testimonial.TestimonialResponseDTO;

import com.cms.controller.dto.utils.PageResponseDTO;
import com.cms.model.testimonial.Testimonial;
import com.cms.services.EditorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("/editor")
@Tag(name = "Editor", description = "Operaciones relacionadas con la gestión del editor")
public class EditorControllerREST {


    private final EditorService editorService;

    public EditorControllerREST(EditorService editorService) {
        this.editorService = editorService;
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
    public ResponseEntity<TestimonialResponseDTO> advance(
            @PathVariable Long idTestimonial,
            @RequestAttribute("userId") Long idEditor) {
        TestimonialResponseDTO response = TestimonialResponseDTO.fromModel(editorService.advanceByEditor(idTestimonial, idEditor));
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/asoc/testimonial/{idTestimonial}")
    @EditorEndpoint
    @Operation(
            summary = "Asociar testimonio al editor",
            description = "Asigna un testimonio en estado Borrador al editor, agregándolo a su lista de drafts para su revisión."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Testimonio asociado correctamente", content = @Content),
            @ApiResponse(responseCode = "404", description = "Testimonio o editor no encontrado", content = @Content),
            @ApiResponse(responseCode = "403", description = "Acceso denegado", content = @Content)
    })
    public ResponseEntity<Void> asocTestimonial(
            @PathVariable Long idTestimonial,
            @RequestAttribute("userId") Long idEditor){
        editorService.asocTestimonial(idTestimonial, idEditor);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/bank/testimonial")
    @EditorEndpoint
    public ResponseEntity<PageResponseDTO<TestimonialResponseDTO>> getTestimonialsToBank(
            @RequestAttribute("userId") Long idEditor,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Page<Testimonial> testimonialPage = editorService.getTestimonialsToBank(idEditor,page,size);

        Page<TestimonialResponseDTO> response = testimonialPage.map(TestimonialResponseDTO::fromModel);

        return ResponseEntity.ok(PageResponseDTO.from(response));
    }
}
