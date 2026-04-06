package com.cms.configuration.data.impl;

import com.cms.configuration.data.DataSeeder;
import com.cms.model.embeds.Embed;
import com.cms.model.user.impl.admin.Admin;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.enums.StateTestimonial;
import com.cms.model.user.impl.Editor;
import com.cms.services.EmbedService;
import com.cms.services.TestimonialService;
import com.cms.services.UserService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("dev")
public class DataSeederImpl implements DataSeeder {

    private final UserService userService;
    private final EmbedService embedService;
    private final TestimonialService testimonialService;

    private Editor editor;
    private Admin admin;

    public DataSeederImpl(UserService userService, EmbedService embedService, TestimonialService testimonialService) {
        this.userService = userService;
        this.embedService = embedService;
        this.testimonialService = testimonialService;
    }

    @Override
    public void run(String... args) {
        admin = Admin.builder()
                .email("admin@gmail.com")
                .password("123")
                .firstName("admin")
                .lastName("administra")
                .build();

        Admin adminSaved = (Admin) userService.save(admin);

        editor = Editor.builder()
                .email("editor@gmail.com")
                .password("123")
                .firstName("editor")
                .lastName("edita")
                .createdBy(adminSaved)
                .build();


        embedService.registerEmbed(adminSaved.getId(), new Embed());
        Embed embed = embedService.registerEmbed(adminSaved.getId(), new Embed());

        userService.save(editor);

        seedTestimonials(embed.getId());
    }

    private void seedTestimonials(Long embedId) {
        List<Testimonial> testimonials = List.of(
                Testimonial.builder()
                        .testimonial("Excelente servicio, lo recomiendo totalmente.")
                        .rating(5)
                        .email("maria@gmail.com")
                        .state(StateTestimonial.APPROVED)
                        .build(),
                Testimonial.builder()
                        .testimonial("Muy buena atención, quedé muy conforme.")
                        .rating(4)
                        .email("carlos@gmail.com")
                        .state(StateTestimonial.APPROVED)
                        .build(),
                Testimonial.builder()
                        .testimonial("El producto superó mis expectativas.")
                        .rating(5)
                        .email("lucia@gmail.com")
                        .state(StateTestimonial.DRAFT)
                        .build(),
                Testimonial.builder()
                        .testimonial("Buena experiencia en general.")
                        .rating(3)
                        .email("jorge@gmail.com")
                        .state(StateTestimonial.DRAFT)
                        .build()
        );

        testimonials.forEach(t -> testimonialService.save(t, embedId, null, "https://www.youtube.com/watch?v=KhXTwEypI6c", request.idCategoria()));
    }
}