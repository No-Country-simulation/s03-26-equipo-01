package com.nocountry.testimonial_cms.repository;

import com.nocountry.testimonial_cms.entities.Testimonial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestimonialRepository extends JpaRepository<Testimonial, Long> {

    // ¡Con solo extender JpaRepository ya tenemos métodos como save(), findAll(), findById(), deleteById()!

    // Más adelante, si necesitamos búsquedas personalizadas para el CMS, las agregaremos aquí.
    // Por ejemplo: List<Testimonial> findByStatus(Testimonial.Status status);
}