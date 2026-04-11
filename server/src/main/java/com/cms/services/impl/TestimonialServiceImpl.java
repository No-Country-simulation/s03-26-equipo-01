package com.cms.services.impl;

import com.cms.exception.EntityNotFoundException;
import com.cms.model.embeds.Embed;
import com.cms.model.testimonial.Media;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.user.impl.admin.Admin;
import com.cms.persistence.repository.TestimonialRepository;
import com.cms.persistence.sql.AdminSQLDAO;
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

    private final TagSQLDAO  tagDAO;

    public TestimonialServiceImpl(TestimonialRepository testimonialRepository,
                                  EmbedService embedService, MediaService mediaService,
                                  AdminSQLDAO adminSQLDAO, TagSQLDAO tagDAO) {

        this.testimonialRepository = testimonialRepository;

        this.embedService = embedService;

        this.mediaService = mediaService;

        this.adminSQLDAO = adminSQLDAO;

        this.tagDAO = tagDAO;
    }

    @Override
    public Testimonial save(Testimonial model, Long idEmbed, Admin admin, MultipartFile image, String youtubeUrl, List<Long> idTags) {
        Embed embed = embedService.findById(idEmbed);
        Media media = mediaService.save(image, youtubeUrl);

        model.setAdmin(admin);
        model.setEmbed(embed);
        model.setMedia(media);
        model.agregarTags(tagDAO.findAllById(idTags));

        Testimonial testimonial = testimonialRepository.save(model);

        admin.addTestimonial(testimonial);

        adminSQLDAO.save(admin);

        return testimonial;
    }

    @Override
    public Testimonial findTestimonialById(Long id) {
        return testimonialRepository.findById(id);
    }

    @Override
    public List<Testimonial> findTestimonialByAdmin(Long idAdmin) {
        if (!adminSQLDAO.existsById(idAdmin)) {
            throw new EntityNotFoundException(Admin.class.getName(), idAdmin);
        }
        return testimonialRepository.findByAdminId(idAdmin);
    }

    @Override
    public Testimonial advanceByEditor(Long id) {
        Testimonial testimonial = findTestimonialById(id);
        testimonial.nextStateEditor();
        return testimonialRepository.update(testimonial);
    }

    @Override
    public Testimonial advanceByAdmin(Long idTestimonial) {
        Testimonial testimonial = findTestimonialById(idTestimonial);

        testimonial.nextStateAdmin();

        return testimonialRepository.update(testimonial);
    }

    @Override
    public void update(Testimonial recovered) {
        testimonialRepository.update(recovered);
    }
}
