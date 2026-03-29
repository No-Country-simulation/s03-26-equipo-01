package com.cms.services;

import com.cms.model.embed.Embed;

public interface EmbedService {
    Embed save(Embed embed);

    Embed findById(Long idEmbed);
}
