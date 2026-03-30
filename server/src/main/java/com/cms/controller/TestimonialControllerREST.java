package com.cms.controller;

import com.cms.controller.dto.testimonial.TestimonialRequestDTO;
import com.cms.controller.dto.testimonial.TestimonialResponseDTO;
import com.cms.model.testimonial.Testimonial;
import com.cms.services.TestimonialService;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testimonial")
@Tag(name = "Testimonio", description = "Endpoints para la gestión de testimonios")
public class TestimonialControllerREST {

    private final TestimonialService testimonialService;

    public TestimonialControllerREST(TestimonialService testimonialService) {
        this.testimonialService = testimonialService;
    }


    @PostMapping
    @SecurityRequirements()
    public ResponseEntity<?> testify(@RequestBody @Valid TestimonialRequestDTO request){
        Testimonial testimonial = testimonialService.save(request.toModel(), request.idEmbed());
        TestimonialResponseDTO response = TestimonialResponseDTO.fromModel(testimonial);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
