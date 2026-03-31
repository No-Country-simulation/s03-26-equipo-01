package com.cms.services.impl;

import com.cms.model.embeds.Embed;
import com.cms.model.testimonial.Testimonial;
import com.cms.persistence.TestimonialRepository;
import com.cms.services.EmbedService;
import com.cms.services.TestimonialService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TestimonialServiceImpl implements TestimonialService {

    private final TestimonialRepository testimonialRepository;
    private final EmbedService embedService;

    public TestimonialServiceImpl(TestimonialRepository testimonialRepository, EmbedService embedService) {
        this.testimonialRepository = testimonialRepository;
        this.embedService = embedService;
    }

    @Override
    public Testimonial save(Testimonial model, Long idEmbed) {
        Embed embed = embedService.findById(idEmbed);

        model.setEmbed(embed);

        return testimonialRepository.save(model);
    }

    @Override
    public Testimonial findTestimonialById(Long id) {
        return testimonialRepository.findById(id);
    }
}
