package com.nocountry.testimonial_cms.service;

import com.nocountry.testimonial_cms.entities.Testimonial;
import com.nocountry.testimonial_cms.repository.TestimonialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestimonialService {

    // Inyectamos el repositorio para poder usar sus métodos
    private final TestimonialRepository testimonialRepository;

    /**
     * Guarda un nuevo testimonio en la base de datos
     */
    public Testimonial createTestimonial(Testimonial testimonial) {
        // Por ahora lo guardamos directo.
        // Más adelante agregaremos validaciones y lógica de YouTube/Cloudinary aquí.
        return testimonialRepository.save(testimonial);
    }

    /**
     * Obtiene la lista de todos los testimonios
     */
    public List<Testimonial> getAllTestimonials() {
        return testimonialRepository.findAll();
    }
}