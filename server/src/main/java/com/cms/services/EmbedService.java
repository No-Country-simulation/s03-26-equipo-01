package com.cms.services;

import com.cms.model.embeds.Embed;
import com.cms.model.testimonial.Tag;
import com.cms.model.testimonial.Testimonial;
import java.util.List;
import com.cms.model.user.impl.admin.Admin;

public interface EmbedService {
    Embed registerEmbed(Long adminId , Embed embed);

    Embed findById(Long idEmbed);

    List<Testimonial> getTestimonialEmbed();

    List<Long> findAllIdsByAdmin(Admin admin);

}
