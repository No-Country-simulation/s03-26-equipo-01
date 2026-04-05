package com.cms.services.impl;

import com.cms.model.embeds.Embed;
import com.cms.model.testimonial.Media;
import com.cms.model.testimonial.Testimonial;
import com.cms.persistence.TestimonialRepository;
import com.cms.services.EmbedService;
import com.cms.services.ImageService;
import com.cms.services.TestimonialService;
import com.cms.services.YoutubeService;
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
    private final YoutubeService youtubeService;

    public TestimonialServiceImpl(TestimonialRepository testimonialRepository,
                                  ImageService imageService,
                                  EmbedService embedService,
                                  YoutubeService youtubeService) {
        this.testimonialRepository = testimonialRepository;
        this.imageService = imageService;
        this.embedService = embedService;
        this.youtubeService = youtubeService;
    }

    @Override
    public Testimonial save(Testimonial model, Long idEmbed, MultipartFile image, String youtubeUrl) {
        Embed embed = embedService.findById(idEmbed);

        Media media = Media.builder().build();

        media.setImageData(imageService.guardarImagen(image));

        media.setVideoData(youtubeService.fromUrl(youtubeUrl));

        model.setEmbed(embed);
        model.setImage(media);

        return testimonialRepository.save(model);
    }

    @Override
    public Testimonial findTestimonialById(Long id) {
        return testimonialRepository.findById(id);
    }
}
