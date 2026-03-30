package com.cms.controller;


import com.cms.model.embeds.dto.DateEmbedsRequestDTO;
import com.cms.model.embeds.dto.DateEmbedsResponseDTO;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
public class EmbedsController {

    private final EmbedService embedService;
    public EmbedsController(EmbedService embedService) {
        this.embedService = embedService;
    }

    @PostMapping("/embeds/registro/{idAdmin}")
    public ResponseEntity<DateEmbedsResponseDTO> registerEmbeds(
            @PathVariable Long idAdmin,
            @RequestBody @Valid DateEmbedsRequestDTO request) {

        Embeds embeds = embedService.registerEmbed(idAdmin, request);
        DateEmbedsResponseDTO response = new DateEmbedsResponseDTO(embeds)
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
