package com.cms.services;

import com.cms.controller.dto.embeds.TestimonialEmbedResponseDTO;
import com.cms.model.embeds.Embed;

import java.util.List;


public interface EmbedService {
    Embed registerEmbed(Long adminId , Embed embed);

    Embed findById(Long idEmbed);


    List<TestimonialEmbedResponseDTO> getTestimonialEmbed();
}
