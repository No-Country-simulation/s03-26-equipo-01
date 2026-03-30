package com.cms.controller;


import com.cms.controller.dto.embeds.DateEmbedsResponseDTO;
import com.cms.model.embeds.Embed;
import com.cms.model.embeds.dto.DateEmbedsRequestDTO;

import com.cms.services.EmbedService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/embed")
public class EmbedController {

    private final EmbedService embedService;
    public EmbedController(EmbedService embedService) {
        this.embedService = embedService;
    }

    @PostMapping("registro/{idAdmin}")
    public ResponseEntity<DateEmbedsResponseDTO> registerEmbeds(
            @PathVariable Long idAdmin,
            @RequestBody @Valid DateEmbedsRequestDTO request) {

        Embed embed = embedService.registerEmbed(idAdmin, request.toModel());

        DateEmbedsResponseDTO response = DateEmbedsResponseDTO.fromModel(embed);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
