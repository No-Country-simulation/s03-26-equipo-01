package com.cms.model.embeds.embedService;

import com.cms.model.embeds.Embeds;
import com.cms.model.embeds.EmbedsRepository;
import com.cms.model.embeds.dto.DateEmbedsRequestDTO;
import com.cms.model.embeds.dto.DateEmbedsResponseDTO;
import com.cms.model.embeds.usuariosLocal.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EnbedService {
     private final EmbedsRepository embedRepository;
     private final AdminRepository adminRepository;

    public EnbedService(EmbedsRepository embedRepository,
                        AdminRepository adminRepository) {
        this.embedRepository = embedRepository;
        this.adminRepository = adminRepository;
    }

    public DateEmbedsResponseDTO registerEmbed(Long adminId ,DateEmbedsRequestDTO request) {
        var admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "El Admin no pudo ser encontrado, id: " + adminId
                ));

        String GenerateToken = UUID.randomUUID().toString();

        var embeds = new Embeds();
        embeds.setEmbedToken(GenerateToken);
        embeds.setAdmin(admin);

        admin.getEmbeds().add(embeds);

        embedRepository.save(embeds);

        return DateEmbedsResponseDTO(embeds);
    }

    public Embeds validateTokenFromEmbeds(String token) {
        return embedRepository.findByEmbedToken(token)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Token inválido"
                ));
    }
private DateEmbedsResponseDTO DateEmbedsResponseDTO(Embeds embedsRegister){
return new DateEmbedsResponseDTO(
        embedsRegister.getEmbedId(),
        embedsRegister.getAdmin().getId(),
        embedsRegister.getEmbedToken());
    }

}
