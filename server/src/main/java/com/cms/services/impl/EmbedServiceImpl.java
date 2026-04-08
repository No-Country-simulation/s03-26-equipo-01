package com.cms.services.impl;


import com.cms.exception.EntityNotFoundException;
import com.cms.model.embeds.Embed;
import com.cms.model.testimonial.Tag;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.enums.StateTestimonial;

import com.cms.model.user.impl.admin.Admin;

import com.cms.persistence.sql.AdminSQLDAO;
import com.cms.persistence.sql.EmbedSQLDAO;
import com.cms.persistence.sql.TagSQLDAO;
import com.cms.persistence.sql.TestimonialSQLDAO;
import com.cms.services.EmbedService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Transactional
public class EmbedServiceImpl implements EmbedService {
     private final EmbedSQLDAO embedSQLDAO;
     private final AdminSQLDAO adminSQLDAO;
     private final TestimonialSQLDAO testimonialSQLDAO;

    public EmbedServiceImpl(EmbedSQLDAO embedSQLDAO, AdminSQLDAO adminSQLDAO,TestimonialSQLDAO testimonialSQLDAO) {
        this.embedSQLDAO = embedSQLDAO;
        this.adminSQLDAO = adminSQLDAO;
        this.testimonialSQLDAO = testimonialSQLDAO;
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
    public List<Testimonial> getTestimonialEmbed(int pageNumber) {
        return testimonialSQLDAO.findTopByState(StateTestimonial.PUBLISHED, PageRequest.of(pageNumber, 5));
    }

    @Override
    public List<Long> findAllIdsByAdmin(Admin admin) {
        return embedSQLDAO.findAllByAdmin(admin);
    }


}
