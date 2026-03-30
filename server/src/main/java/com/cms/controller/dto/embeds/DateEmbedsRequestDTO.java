package com.cms.model.embeds.dto;


import com.cms.model.embeds.Embed;

public record DateEmbedsRequestDTO(){

    public Embed toModel() {
        return new Embed();
    }
}