package com.cms.controller.dto.embeds;


import com.cms.model.embeds.Embed;

public record DateEmbedsResponseDTO(
    Long id,
    Long adminId

) {

    public static DateEmbedsResponseDTO fromModel(Embed embed) {
        return new DateEmbedsResponseDTO(
            embed.getId(),
            embed.getAdmin().getId()
        );
    }
}
