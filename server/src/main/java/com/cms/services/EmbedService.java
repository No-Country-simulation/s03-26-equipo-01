package com.cms.services;

import com.cms.model.embeds.Embed;
import com.cms.model.embeds.dto.DateEmbedsRequestDTO;


public interface EmbedService {
    Embed registerEmbed(Long adminId , Embed embed);
}
