package com.cms.services.impl;

import com.cms.exception.EntityNotFoundException;
import com.cms.model.embeds.Embed;
import com.cms.model.user.impl.admin.Admin;

import com.cms.persistence.sql.AdminSQLDAO;
import com.cms.persistence.sql.EmbedSQLDAO;
import com.cms.services.EmbedService;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
@Transactional
public class EmbedServiceImpl implements EmbedService {
     private final EmbedSQLDAO embedSQLDAO;
     private final AdminSQLDAO adminSQLDAO;

    public EmbedServiceImpl(EmbedSQLDAO embedSQLDAO, AdminSQLDAO adminSQLDAO) {
        this.embedSQLDAO = embedSQLDAO;
        this.adminSQLDAO = adminSQLDAO;
    }


    public Embed registerEmbed (Long adminId , Embed embed) {

        Admin admin = adminSQLDAO.findByIdAndEnabledTrue(adminId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "El Admin no pudo ser encontrado o no está habilitado, id: " + adminId
                ));

        embed.setAdmin(admin);

        admin.agregarEmbed(embed);

        return embedSQLDAO.save(embed);
    }


    @Override
    public Embed findById(Long idEmbed) {
        return embedSQLDAO.findById(idEmbed).orElseThrow(() -> new EntityNotFoundException(Embed.class.getName(), idEmbed));
    }

    @Override
    public List<Long> findAllIdsByAdmin(Admin admin) {
        return embedSQLDAO.findAllByAdmin(admin);
    }
}
