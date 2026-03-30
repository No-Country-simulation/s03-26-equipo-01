package com.cms.model.embeds.embedService;

import com.cms.model.embeds.Embeds;
import com.cms.model.embeds.EmbedsRepository;
import com.cms.model.embeds.dto.DateEmbedsRequestDTO;
import com.cms.model.embeds.dto.DateEmbedsResponseDTO;
import com.cms.model.embeds.usuariosLocal.AdminRepository;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmbedServiceImpl implements EnbedService {
     private final EmbedsRepository embedRepository;
     private final AdminSQLDAO adminRepository;

    public EnbedServiceImpl(EmbedsRepository embedRepository, AdminSQLDAO adminRepository) {
        this.embedRepository = embedRepository;
        this.adminRepository = adminRepository;
    }

    public Embeds registerEmbed (Long adminId ,DateEmbedsRequestDTO request) {

        Admin admin = adminRepository.findByIdAndEnabledTrue(adminId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "El Admin no pudo ser encontrado o no está habilitado, id: " + adminId
                ));

        Embeds embeds = new Embeds();

        embeds.setAdmin(admin);

        admin.getEmbeds().add(embeds);

        embedRepository.save(embeds);

        return embeds;
    }
}
