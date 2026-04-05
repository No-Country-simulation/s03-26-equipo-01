package com.cms.model.testimonial;

import com.cms.model.user.impl.admin.Admin;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Category {

    private Long id;

    private String name;

    private String slug;

    private String description;

    @Builder.Default
    private Boolean deleted = false;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();

    private Admin creator;
}