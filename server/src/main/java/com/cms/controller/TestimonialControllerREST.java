package com.cms.controller;

import com.cms.controller.dto.testimonial.TestimonialRequestDTO;
import com.cms.controller.dto.testimonial.TestimonialResponseDTO;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.user.impl.admin.Admin;
import com.cms.services.TestimonialService;
import com.cms.utils.AuthUtils;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
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
                request.idEmbed(),
                admin,
                request.image(),
                request.youtubeUrl(),
                request.idTags()
        );

        TestimonialResponseDTO response = TestimonialResponseDTO.fromModel(testimonial);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}