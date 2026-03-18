package com.nocountry.testimonial_cms.controller;

import com.nocountry.testimonial_cms.entities.Testimonial;
import com.nocountry.testimonial_cms.service.TestimonialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/testimonials")
@RequiredArgsConstructor
public class TestimonialController {

    private final TestimonialService testimonialService;

    /**
     * Endpoint para crear un nuevo testimonio.
     * El Frontend enviará un JSON que Spring convertirá automáticamente en un objeto Testimonial.
     */
    @PostMapping
    public ResponseEntity<Testimonial> createTestimonial(@RequestBody Testimonial testimonial) {
        Testimonial savedTestimonial = testimonialService.createTestimonial(testimonial);
        // Devolvemos el testimonio guardado y un código de estado 201 (Created)
        return new ResponseEntity<>(savedTestimonial, HttpStatus.CREATED);
    }

    /**
     * Endpoint para obtener todos los testimonios.
     */
    @GetMapping
    public ResponseEntity<List<Testimonial>> getAllTestimonials() {
        List<Testimonial> testimonials = testimonialService.getAllTestimonials();
        // Devolvemos la lista y un código de estado 200 (OK)
        return new ResponseEntity<>(testimonials, HttpStatus.OK);
    }
}