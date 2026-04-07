package com.cms.services;

import com.cms.controller.dto.embeds.TestimonialEmbedResponseDTO;
import com.cms.model.embeds.Embed;
import com.cms.model.testimonial.Tag;
import com.cms.model.testimonial.Testimonial;
import java.util.List;
import com.cms.model.user.impl.admin.Admin;

public interface EmbedService {
    Embed registerEmbed(Long adminId , Embed embed);

    Embed findById(Long idEmbed);


    List<Testimonial> getTestimonialEmbed(int pageNumber);

    List<Long> findAllIdsByAdmin(Admin admin);
    List<Tag> findByTestimonialId(Long testimonialId);

//    Metodo para filtrar unicamente el nombre de la tag y no todos sus campos
    TestimonialEmbedResponseDTO convertToDto(Testimonial testimonial);
}
