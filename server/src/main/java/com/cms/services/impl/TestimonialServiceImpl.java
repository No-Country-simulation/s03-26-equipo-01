package com.cms.services.impl;

import com.cms.model.embeds.Embed;
import com.cms.model.testimonial.Media;
import com.cms.model.testimonial.Testimonial;
import com.cms.persistence.TestimonialRepository;
import com.cms.services.EmbedService;
import com.cms.services.ImageService;
import com.cms.services.TestimonialService;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class TestimonialServiceImpl implements TestimonialService {

    private final TestimonialRepository testimonialRepository;
    private final ImageService imageService;
    private final EmbedService embedService;

    public TestimonialServiceImpl(TestimonialRepository testimonialRepository, ImageService imageService, EmbedService embedService) {
        this.testimonialRepository = testimonialRepository;
        this.imageService = imageService;
        this.embedService = embedService;
    }

    @Override
    public Testimonial save(Testimonial model, Long idEmbed, MultipartFile image) {
        Embed embed = embedService.findById(idEmbed);
        Media imageSaved = imageService.guardarImagen(image);

        model.setEmbed(embed);
        model.setImage(imageSaved);

        return testimonialRepository.save(model);
    }

    @Override
    public Testimonial findTestimonialById(Long id) {
        return testimonialRepository.findById(id);
    }
}
