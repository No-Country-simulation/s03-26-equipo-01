package com.cms.model.embeds.dto;


public record DateEmbedsResponseDTO(
    Long id,
    Long adminId

) {
    public DateEmbedsResponseDTO(Embeds embeds) {
        this(embeds.getEmbedId(), embeds.getAdmin().getId());
    }
}
