package com.cms.model.testimonial;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Media {

    @Builder.Default
    private String url = "123";

    @Builder.Default
    private String publicId = "123";
}
