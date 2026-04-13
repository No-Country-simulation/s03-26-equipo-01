package com.cms.configuration.data.impl;

import com.cms.configuration.data.DataSeeder;
import com.cms.model.embeds.Embed;
import com.cms.model.testimonial.Category;
import com.cms.model.testimonial.Tag;
import com.cms.model.user.impl.admin.Admin;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.enums.StateTestimonial;
import com.cms.model.user.impl.Editor;
import com.cms.services.*;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("dev")
public class DataSeederImpl implements DataSeeder {

    private final UserService userService;
    private final AdminService adminService;
    private final EmbedService embedService;
    private final TestimonialService testimonialService;
    private final CategoryService categoryService;
    private final TagService tagService;

    private Editor editor;
    private Admin admin;
    private Admin admin2;

    public DataSeederImpl(UserService userService, AdminService adminService, EmbedService embedService, TestimonialService testimonialService, CategoryService categoryService, TagService tagService) {
        this.userService = userService;
        this.adminService = adminService;
        this.embedService = embedService;
        this.testimonialService = testimonialService;
        this.categoryService = categoryService;
        this.tagService = tagService;
    }

    @Override
    public void run(String... args) {
        admin = Admin.builder()
                .email("admin@gmail.com")
                .password("123")
                .firstName("admin")
                .lastName("administra")
                .build();
        admin2 = Admin.builder()
                .email("admin2@gmail.com")
                .password("123")
                .firstName("admin")
                .lastName("administra")
                .build();

        Admin adminSaved = adminService.save(admin);

        admin2 = adminService.save(admin2);

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
        Category category = categoryService.create(
                Category.builder()
                        .name("Test Category")
                        .slug("test-category")
                        .build(),
                admin.getId()
        );

        Category category2 = categoryService.create(
                Category.builder()
                        .name("Test Category2")
                        .slug("test-category2")
                        .build(),
                admin2.getId()
        );

        Tag tag1 = tagService.create(Tag.builder().name("backend").build(), admin.getId());

        Tag tag2 = tagService.create(Tag.builder().name("java").build(), admin.getId());

        Tag tag3 = tagService.create(Tag.builder().name("java").build(), admin2.getId());

        List<Long> tagIds = List.of(tag1.getId(), tag2.getId());

        List<Testimonial> testimonials = List.of(
                Testimonial.builder()
                        .testimonial("Excelente servicio, lo recomiendo totalmente.")
                        .witness("Robert")
                        .rating(5)
                        .category(category)
                        .email("maria@gmail.com")
                        .state(StateTestimonial.APPROVED)
                        .build(),
                Testimonial.builder()
                        .testimonial("Muy buena atención, quedé muy conforme.")
                        .witness("Robert2")
                        .rating(4)
                        .category(category)
                        .email("carlos@gmail.com")
                        .state(StateTestimonial.APPROVED)
                        .build(),
                Testimonial.builder()
                        .testimonial("El producto superó mis expectativas.")
                        .witness("Robert3")
                        .rating(5)
                        .category(category)
                        .email("lucia@gmail.com")
                        .state(StateTestimonial.DRAFT)
                        .build(),
                Testimonial.builder()
                        .testimonial("Buena experiencia en general.")
                        .witness("Robert4")
                        .rating(3)
                        .category(category)
                        .email("jorge@gmail.com")
                        .state(StateTestimonial.PUBLISHED)
                        .build(),
                Testimonial.builder()
                        .testimonial("Buena experiencia en general.")
                        .witness("Robert4")
                        .rating(3)
                        .category(category)
                        .email("jorge@gmail.com")
                        .state(StateTestimonial.PUBLISHED)
                        .build(),
                Testimonial.builder()
                        .testimonial("Buena experiencia en general.")
                        .witness("Robert4")
                        .rating(3)
                        .category(category)
                        .email("jorge@gmail.com")
                        .state(StateTestimonial.PUBLISHED)
                        .build(),
                Testimonial.builder()
                        .testimonial("Buena experiencia en general.")
                        .witness("Robert4")
                        .rating(3)
                        .category(category)
                        .email("jorge@gmail.com")
                        .state(StateTestimonial.PUBLISHED)
                        .build(),
                Testimonial.builder()
                        .testimonial("Buena experiencia en general.")
                        .witness("Robert4")
                        .rating(3)
                        .category(category)
                        .email("jorge@gmail.com")
                        .state(StateTestimonial.PUBLISHED)
                        .build(),
                Testimonial.builder()
                        .testimonial("Buena experiencia en general.")
                        .witness("Robert4")
                        .rating(3)
                        .category(category)
                        .email("jorge@gmail.com")
                        .state(StateTestimonial.PUBLISHED)
                        .build(),
                Testimonial.builder()
                        .testimonial("Buena experiencia en general.")
                        .witness("Robert4")
                        .rating(3)
                        .category(category)
                        .email("jorge@gmail.com")
                        .state(StateTestimonial.PUBLISHED)
                        .build(),
                Testimonial.builder()
                        .testimonial("Buena experiencia en general.")
                        .witness("Robert4")
                        .rating(3)
                        .category(category)
                        .email("jorge@gmail.com")
                        .state(StateTestimonial.PUBLISHED)
                        .build()
        );

        testimonials.forEach(t -> testimonialService.save(t, admin, null, "https://www.youtube.com/watch?v=KhXTwEypI6c", tagIds));
    }
}
