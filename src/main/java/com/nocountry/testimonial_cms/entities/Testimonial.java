package com.nocountry.testimonial_cms.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "testimonials")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Testimonial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    private String imageUrl;

    private String youtubeVideoId;

    // Guardamos el Enum como un String en la base de datos (ej: "PENDING")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    // Esta función se ejecuta automáticamente justo antes de guardar en la BD por primera vez
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = Status.PENDING; // Por defecto, todo entra como pendiente
        }
    }

    // Definimos los estados posibles del testimonio
    public enum Status {
        PENDING, PUBLICADO, RECHAZADO
    }
}