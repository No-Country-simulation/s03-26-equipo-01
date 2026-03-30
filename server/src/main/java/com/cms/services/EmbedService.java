package com.cms.services;

import com.cms.model.embeds.Embeds;
import com.cms.model.embeds.dto.DateEmbedsRequestDTO;


public interface EmbedService {
    Embeds registerEmbed(Long adminId , DateEmbedsRequestDTO request);
}
