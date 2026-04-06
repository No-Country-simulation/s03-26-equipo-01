package com.cms.persistence.sql;


import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.enums.StateTestimonial;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface TestimonialSQLDAO extends JpaRepository<Testimonial, Long> {

    //Traemos a 5 testimonios publicados si hay más se puede usar la paginación para traer más de 5 testimonios en caso de que exista
    @Query("SELECT t FROM Testimonial t WHERE t.state = :state ORDER BY t.createdAt DESC")
    List<Testimonial> findTopByState(@Param("state") StateTestimonial state, Pageable pageable);
    @Query("SELECT t FROM Testimonial t WHERE t.embed.id IN :ids")
    List<Testimonial> findAllByEmbedIs(@Param("ids") List<Long> ids);
}
