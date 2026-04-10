package com.cms.services.impl;

import com.cms.exception.EntityNotFoundException;
import com.cms.model.embeds.Embed;
import com.cms.model.testimonial.Media;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.enums.StateTestimonial;
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
    public Testimonial save(Testimonial model, Long idEmbed, MultipartFile image, String youtubeUrl, List<Long> idTags) {
        Embed embed = embedService.findById(idEmbed);
        Media media = mediaService.save(image, youtubeUrl);

        model.setEmbed(embed);
        model.setMedia(media);
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

    @Override
    public Testimonial evaluateTestimonialState(Testimonial testimonial) {
         StateTestimonial state = testimonial.getState();
        if (state == StateTestimonial.DRAFT || state == StateTestimonial.PENDING) {
            throw new IllegalStateException(
                    "No se puede eliminar testimonio para este estado " + state.getLabel()
            );
        }
        return testimonial;
    }

    @Override
    @Transactional
    public Testimonial deleteTestimonial(Long id, Long idAdmin) {

            Testimonial testimonial = testimonialRepository.findTestimonialByIdAndAdminId(id, idAdmin).orElseThrow(() -> new EntityNotFoundException(Testimonial.class.getName(), id));
            evaluateTestimonialState(testimonial);
            mediaService.deleteVideo(testimonial.getMedia().getVideoId());
            mediaService.deleteImage(testimonial.getMedia().getPublicId());
            testimonialRepository.deleteById(id);
            return testimonial;


    }
}
