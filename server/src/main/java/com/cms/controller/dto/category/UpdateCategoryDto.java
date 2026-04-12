package com.cms.controller.dto.category;

import com.cms.model.testimonial.Category;
import java.text.Normalizer;
import java.util.Locale;

public record UpdateCategoryDto(
        String name
) {
        public void aplicar(Category category) {

                if (this.name() != null && !this.name().isBlank()) {
                        category.setName(this.name().trim());
                        category.setSlug(generarSlug(this.name()));
                }
        }


        private String generarSlug(String input) {
                String noWhitespace = input.trim().toLowerCase(Locale.ENGLISH);
                String normalized = Normalizer.normalize(noWhitespace, Normalizer.Form.NFD);
                String slug = normalized.replaceAll("[^\\p{ASCII}]", ""); // Saca las tildes
                return slug.replaceAll("[\\s+]", "-").replaceAll("[^a-z0-9-]", ""); // Cambia espacios por guiones
        }
}