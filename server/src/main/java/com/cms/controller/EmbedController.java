package com.cms.controller;

import com.cms.controller.dto.embeds.DateEmbedsResponseDTO;
import com.cms.controller.dto.testimonial.TestimonialPublicDTO;
import com.cms.model.embeds.Embed;
import com.cms.model.embeds.dto.DateEmbedsRequestDTO;
import com.cms.model.testimonial.Testimonial;
import com.cms.services.EmbedService;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/embed")
@Tag(name = "Embeds", description = "Endpoint relacionadas con la gestión de embeds y testimonios en el CMS")
public class EmbedController {

    private final EmbedService embedService;
    public EmbedController(EmbedService embedService) {
        this.embedService = embedService;
    }

    @Operation(summary = "Registrar nuevo embeds", description = "Registra nuevos embeds para un administrador específico y devuelve los datos del embed creado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Embeds  registrados con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada no válidos"),
            @ApiResponse(responseCode = "404", description = "Administrator no encontrado")
    })
    @PostMapping("registro/{idAdmin}")
    public ResponseEntity<DateEmbedsResponseDTO> registerEmbeds(
            @PathVariable Long idAdmin,
            @RequestBody @Valid DateEmbedsRequestDTO request) {

        Embed embed = embedService.registerEmbed(idAdmin, request.toModel());

        DateEmbedsResponseDTO response = DateEmbedsResponseDTO.fromModel(embed);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Testimonio Publicados", description = "Recupera una lista de todos los testimonios publicados disponibles para incrustar.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Testimonios recuperados con éxito"),
            @ApiResponse(responseCode = "404", description = "testimonio no encontrado ")
    })
    @PostMapping("/published")
    public ResponseEntity<List<TestimonialPublicDTO>> testimonialPublished(){
       List<Testimonial> testimonialEmbed = embedService.getTestimonialEmbed();

       List<TestimonialPublicDTO> response = testimonialEmbed.stream().map(TestimonialPublicDTO::fromModel).toList();

        return ResponseEntity.ok(response);
    }


}
