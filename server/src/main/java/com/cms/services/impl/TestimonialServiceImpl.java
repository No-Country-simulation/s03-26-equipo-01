package com.cms.services.impl;

import com.cms.exception.EntityNotFoundException;
import com.cms.model.embeds.Embed;
import com.cms.model.testimonial.Media;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.user.impl.admin.Admin;
import com.cms.persistence.repository.TestimonialRepository;
import com.cms.persistence.sql.AdminSQLDAO;
import com.cms.persistence.sql.CategorySQLDAO;
import com.cms.persistence.sql.TagSQLDAO;
import com.cms.services.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TestimonialServiceImpl implements TestimonialService {

    private final TestimonialRepository testimonialRepository;

    private final EmbedService embedService;

    private final MediaService mediaService;

    private final AdminSQLDAO adminSQLDAO;

    private final CategorySQLDAO categoryDAO;

    private final TagSQLDAO  tagDAO;

    public TestimonialServiceImpl(TestimonialRepository testimonialRepository,
                                  EmbedService embedService, MediaService mediaService,
                                  AdminSQLDAO adminSQLDAO, CategorySQLDAO categoryDAO, TagSQLDAO tagDAO) {

        this.testimonialRepository = testimonialRepository;

        this.embedService = embedService;

        this.mediaService = mediaService;

        this.adminSQLDAO = adminSQLDAO;

        this.categoryDAO = categoryDAO;
        this.tagDAO = tagDAO;
    }

    @Override
    public Testimonial save(Testimonial model, Long idEmbed, MultipartFile image, String youtubeUrl, Long idCategory, List<Long> idTags) {
        Embed embed = embedService.findById(idEmbed);
        Media media = mediaService.save(image, youtubeUrl);

        model.setEmbed(embed);
        model.setMedia(media);
        model.setCategory(categoryDAO.findById(idCategory).orElse(null));
        model.agregarTags(tagDAO.findAllById(idTags));

        return testimonialRepository.save(model);
    }

    @Override
    public Testimonial findTestimonialById(Long id) {
        return testimonialRepository.findById(id);
    }

    @Override
    public List<Testimonial> findTestimonialByAdmin(Long idAdmin) {
        Admin admin = adminSQLDAO.findById(idAdmin).orElseThrow(() -> new EntityNotFoundException(Admin.class.getName(), idAdmin));
        List<Long> embedIds = embedService.findAllIdsByAdmin(admin);
        return testimonialRepository.findTestimonialByEmbeds(embedIds);
    }
}
