package com.cms.services;

import com.cms.controller.dto.embeds.TestimonialEmbedResponseDTO;
import com.cms.model.embeds.Embed;
import com.cms.model.testimonial.Testimonial;

import java.util.List;
import com.cms.model.embeds.dto.DateEmbedsRequestDTO;
import com.cms.model.user.impl.admin.Admin;

import java.util.List;


public interface EmbedService {
    Embed registerEmbed(Long adminId , Embed embed);

    Embed findById(Long idEmbed);


    List<Testimonial> getTestimonialEmbed();

    List<Long> findAllIdsByAdmin(Admin admin);
}
