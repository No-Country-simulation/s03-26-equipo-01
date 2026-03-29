package com.cms.services.impl;

import com.cms.exception.EntityNotFoundException;
import com.cms.model.embed.Embed;
import com.cms.model.testimonial.Testimonial;
import com.cms.persistence.SQL.EmbedSQLDAO;
import com.cms.services.EmbedService;
import org.springframework.stereotype.Service;

@Service
public class EmbedServiceImpl implements EmbedService {

    private final EmbedSQLDAO embedSQLDAO;


    public EmbedServiceImpl(EmbedSQLDAO embedSQLDAO) {
        this.embedSQLDAO = embedSQLDAO;
    }


    @Override
    public Embed save(Embed embed) {
        return embedSQLDAO.save(embed);
    }

    @Override
    public Embed findById(Long idEmbed) {
        return embedSQLDAO.findById(idEmbed).orElseThrow(() -> new EntityNotFoundException(Embed.class.getName(), idEmbed));
    }
}
