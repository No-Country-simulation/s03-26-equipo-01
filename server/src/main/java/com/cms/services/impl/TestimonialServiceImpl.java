package com.cms.services.impl;

import com.cms.exception.EntityNotFoundException;
import com.cms.exception.business.BusinessException;
import com.cms.model.embeds.Embed;
import com.cms.model.testimonial.Media;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.enums.StateTestimonial;
import com.cms.model.user.impl.admin.Admin;
import com.cms.persistence.repository.TestimonialRepository;
import com.cms.persistence.sql.AdminSQLDAO;
import com.cms.services.EmbedService;
import com.cms.services.ImageService;
import com.cms.services.TestimonialService;
import com.cms.services.YoutubeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TestimonialServiceImpl implements TestimonialService {

    private final TestimonialRepository testimonialRepository;
    private final ImageService imageService;
    private final EmbedService embedService;
    private final YoutubeService youtubeService;
    private final AdminSQLDAO adminSQLDAO;

    public TestimonialServiceImpl(TestimonialRepository testimonialRepository,
                                  ImageService imageService,
                                  EmbedService embedService,
                                  YoutubeService youtubeService,
                                  AdminSQLDAO adminSQLDAO) {
        this.testimonialRepository = testimonialRepository;
        this.imageService = imageService;
        this.embedService = embedService;
        this.youtubeService = youtubeService;
        this.adminSQLDAO = adminSQLDAO;
    }

    @Override
    public Testimonial save(Testimonial model, Long idEmbed, MultipartFile image, String youtubeUrl) {
        Embed embed = embedService.findById(idEmbed);
        model.setEmbed(embed);
        return testimonialRepository.save(model, image, youtubeUrl);
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
    public Testimonial nextState(Long id, String role) {
        Testimonial testimonial = findTestimonialById(id);

        if (testimonial.getState() == StateTestimonial.DRAFT && !role.equals("ROLE_EDITOR")) {
            throw new BusinessException("Solo un editor puede aprobar un testimonio borrador");
        }
        if (testimonial.getState() != StateTestimonial.DRAFT && !role.equals("ROLE_ADMIN")) {
            throw new BusinessException("Solo un admin puede avanzar este estado");
        }

        testimonial.nextState();
        return testimonialRepository.update(testimonial);
    }
}
