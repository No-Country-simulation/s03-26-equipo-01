package com.cms.services.impl;

import com.cms.model.embeds.EmbedSQLDAO;
import com.cms.model.embeds.Embed;
import com.cms.model.user.impl.Admin;
import com.cms.persistence.SQL.AdminSQLDAO;
import com.cms.services.EmbedService;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmbedServiceImpl implements EmbedService {
     private final EmbedSQLDAO embedRepository;
     private final AdminSQLDAO adminRepository;

    public EmbedServiceImpl(EmbedSQLDAO embedRepository, AdminSQLDAO adminRepository) {
        this.embedRepository = embedRepository;
        this.adminRepository = adminRepository;
    }


    public Embed registerEmbed (Long adminId , Embed embed) {

        Admin admin = adminRepository.findByIdAndEnabledTrue(adminId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "El Admin no pudo ser encontrado o no está habilitado, id: " + adminId
                ));

        embed.setAdmin(admin);

        admin.agregarEmbed(embed);

        return embedRepository.save(embed);
    }
}
