package com.cms.controller;

import com.cms.controller.dto.testimonial.TestimonialRequestDTO;
import com.cms.services.TestimonialService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Testimonial")
@Tag(name = "Testimonio", description = "Endpoints para la gestión de testimonios")
public class TestimonialControllerREST {

    private final TestimonialService testimonialService;

    public TestimonialControllerREST(TestimonialService testimonialService) {
        this.testimonialService = testimonialService;
    }


    @PostMapping
    public ResponseEntity<?> testify(@RequestBody @Valid TestimonialRequestDTO request){
        testimonialService.save(request.toModel(), request.idEmbed());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
