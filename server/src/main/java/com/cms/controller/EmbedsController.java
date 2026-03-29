package com.cms.controller;


import com.cms.model.embeds.dto.DateEmbedsRequestDTO;
import com.cms.model.embeds.dto.DateEmbedsResponseDTO;
import com.cms.model.embeds.embedService.EnbedService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
public class EmbedsController {

    private final EnbedService embedService;
    public EmbedsController(EnbedService embedService) {
        this.embedService = embedService;
    }

    @PostMapping("/embeds/registro/{idAdmin}")
    public ResponseEntity<DateEmbedsResponseDTO> registerEmbeds(
            @PathVariable Long idAdmin,
            @RequestBody @Valid DateEmbedsRequestDTO request) {

        DateEmbedsResponseDTO response = embedService.registerEmbed(idAdmin, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


}
