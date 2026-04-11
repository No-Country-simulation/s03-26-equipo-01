package com.cms.controller;

import com.cms.controller.dto.testimonial.TestimonialPublicDTO;
import com.cms.controller.dto.testimonial.TestimonialRequestDTO;
import com.cms.controller.dto.testimonial.TestimonialResponseDTO;
import com.cms.controller.dto.utils.PageResponseDTO;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.enums.StateTestimonial;
import com.cms.model.user.impl.admin.Admin;
import com.cms.services.TestimonialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/testimonial")
@Tag(name = "Testimonio", description = "Endpoints para la gestión de testimonios")
public class TestimonialControllerREST {

    private final TestimonialService testimonialService;


    public TestimonialControllerREST(TestimonialService testimonialService) {
        this.testimonialService = testimonialService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<TestimonialResponseDTO> testify(@ModelAttribute @Valid TestimonialRequestDTO request,
                                                          HttpServletRequest httpRequest){
        Admin admin = (Admin) httpRequest.getAttribute("admin");

        Testimonial testimonial = testimonialService.save(
                request.toModel(),
                admin,
                request.image(),
                request.youtubeUrl(),
                request.idTags()
        );

        TestimonialResponseDTO response = TestimonialResponseDTO.fromModel(testimonial);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Testimonio Publicados", description = "Recupera una lista de todos los testimonios publicados disponibles para incrustar.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Testimonios recuperados con éxito"),
            @ApiResponse(responseCode = "404", description = "testimonio no encontrado ")
    })
    @PostMapping("/published")
    public ResponseEntity<PageResponseDTO<TestimonialPublicDTO>> testimonialPublished(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            HttpServletRequest httpRequest
    ){
        Admin admin = (Admin) httpRequest.getAttribute("admin");

        PageResponseDTO<TestimonialPublicDTO> response = PageResponseDTO.from(
                testimonialService.findAllTestimonial(page,size, admin, StateTestimonial.PUBLISHED).map(TestimonialPublicDTO::fromModel)
        );
        return ResponseEntity.ok(response);
    }
}