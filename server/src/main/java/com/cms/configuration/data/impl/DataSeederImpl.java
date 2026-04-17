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

    private Admin admin;

    public DataSeederImpl(UserService userService, AdminService adminService, EmbedService embedService,
                          TestimonialService testimonialService, CategoryService categoryService, TagService tagService) {
        this.userService = userService;
        this.adminService = adminService;
        this.embedService = embedService;
        this.testimonialService = testimonialService;
        this.categoryService = categoryService;
        this.tagService = tagService;
    }

    @Override
    public void run(String... args) {
        admin = adminService.save(
                Admin.builder()
                        .email("admin@gmail.com")
                        .password("123")
                        .firstName("admin")
                        .lastName("administra")
                        .build()
        );

        embedService.registerEmbed(admin.getId(), new Embed());
        Embed embed = embedService.registerEmbed(admin.getId(), new Embed());

        seedEditors();
        seedTestimonials(embed.getId());
    }

    private void seedEditors() {
        List<Editor> editors = List.of(
                Editor.builder().email("laura.cifuentes@gmail.com").password("123").firstName("Laura").lastName("Cifuentes").createdBy(admin).build(),
                Editor.builder().email("carlos.restrepo@gmail.com").password("123").firstName("Carlos").lastName("Restrepo").createdBy(admin).build(),
                Editor.builder().email("ignacio.gutierrez@gmail.com").password("123").firstName("Ignacio").lastName("Gutiérrez").createdBy(admin).build(),
                Editor.builder().email("diego.montoya@gmail.com").password("123").firstName("Diego").lastName("Montoya").createdBy(admin).build(),
                Editor.builder().email("martin.roldan@gmail.com").password("123").firstName("Martín").lastName("Roldán").createdBy(admin).build(),
                Editor.builder().email("daniel.pereira@gmail.com").password("123").firstName("Daniel").lastName("Pereira").createdBy(admin).build(),
                Editor.builder().email("sofia.paredes@gmail.com").password("123").firstName("Sofía").lastName("Paredes").createdBy(admin).build(),
                Editor.builder().email("andrea.molina@gmail.com").password("123").firstName("Andrea").lastName("Molina").createdBy(admin).build(),
                Editor.builder().email("natalia.fuentes@gmail.com").password("123").firstName("Natalia").lastName("Fuentes").createdBy(admin).build(),
                Editor.builder().email("jorge.villanueva@gmail.com").password("123").firstName("Jorge").lastName("Villanueva").createdBy(admin).build(),
                Editor.builder().email("patricia.guzman@gmail.com").password("123").firstName("Patricia").lastName("Guzmán").createdBy(admin).build()
        );
        editors.forEach(userService::save);
    }

    private void seedTestimonials(Long embedId) {
        // --- Categories ---
        Category catProducto = categoryService.create(
                Category.builder().name("Producto").slug("producto").build(), admin.getId());

        Category catEvento = categoryService.create(
                Category.builder().name("Evento").slug("evento").build(), admin.getId());

        Category catCliente = categoryService.create(
                Category.builder().name("Cliente").slug("cliente").build(), admin.getId());

        Category catIndustria = categoryService.create(
                Category.builder().name("Industria").slug("industria").build(), admin.getId());

        // --- Tags ---
        Tag tagLearningAnalytics = tagService.create(Tag.builder().name("Learning Analytics").build(), admin.getId());
        Tag tagDashboardDocente  = tagService.create(Tag.builder().name("Dashboard docente").build(), admin.getId());
        Tag tagRestApis          = tagService.create(Tag.builder().name("REST APIs").build(), admin.getId());
        Tag tagNodeJs            = tagService.create(Tag.builder().name("Node js").build(), admin.getId());
        Tag tagCambioCarrera     = tagService.create(Tag.builder().name("Cambio de carrera").build(), admin.getId());
        Tag tagCorporativo       = tagService.create(Tag.builder().name("Corporativo").build(), admin.getId());
        Tag tagOnboarding        = tagService.create(Tag.builder().name("Onboarding").build(), admin.getId());
        Tag tagWebinar           = tagService.create(Tag.builder().name("Webinar").build(), admin.getId());
        Tag tagMobileFirst       = tagService.create(Tag.builder().name("Mobile first").build(), admin.getId());
        Tag tagEdTechLatam       = tagService.create(Tag.builder().name("EdTech LATAM").build(), admin.getId());
        Tag tagMarketingDigital  = tagService.create(Tag.builder().name("Marketing digital").build(), admin.getId());

        // ========================
        // Categoría: Producto
        // ========================

        // 1. Aprobado - Laura Cifuentes
        testimonialService.save(
                Testimonial.builder()
                        .testimonial("Desde que empecé a usar la plataforma, mis alumnos llegaron un 40% más preparados a clase. " +
                                "La analítica me permite saber exactamente en qué temas necesitan refuerzo antes de que yo lo descubra por las notas.")
                        .witness("Laura Cifuentes")
                        .rating(4)
                        .category(catProducto)
                        .state(StateTestimonial.APPROVED)
                        .build(),
                admin, null, null,
                List.of(tagLearningAnalytics.getId(), tagDashboardDocente.getId())
        );

        // 2. Pendiente - Carlos Restrepo
        testimonialService.save(
                Testimonial.builder()
                        .testimonial("Llegué sin saber nada de programación. La Ruta de aprendizaje me dio una progresión muy clara: " +
                                "cada curso tenía sentido en relación al anterior. Hoy trabajo en Mercado Libre y uso Node y PostgreSQL todos los días. " +
                                "En 8 meses cambié de carrera.")
                        .witness("Carlos Restrepo")
                        .rating(9)
                        .category(catProducto)
                        .state(StateTestimonial.PENDING)
                        .build(),
                admin, null, null,
                List.of(tagRestApis.getId(), tagNodeJs.getId(), tagCambioCarrera.getId())
        );

        // 3. Borrador - Ignacio Gutiérrez
        testimonialService.save(
                Testimonial.builder()
                        .testimonial("Usamos el módulo de onboarding para incorporar a más de 800 vendedores nuevos en temporada alta. " +
                                "El proceso fue mucho más ordenado que años anteriores. Lo recomendaría a cualquier empresa de retail.")
                        .witness("Ignacio Gutiérrez")
                        .rating(5)
                        .category(catProducto)
                        .state(StateTestimonial.DRAFT)
                        .build(),
                admin, null, null,
                List.of(tagRestApis.getId(), tagNodeJs.getId(), tagCambioCarrera.getId())
        );

        // 4. Borrador - Diego Montoya
        testimonialService.save(
                Testimonial.builder()
                        .testimonial("Capacitamos a todo nuestro equipo de desarrollo con la ruta de aprendizaje de Node.js. " +
                                "En dos meses teníamos a los juniors trabajando en producción. Para una startup eso es todo.")
                        .witness("Diego Montoya")
                        .rating(5)
                        .category(catProducto)
                        .state(StateTestimonial.DRAFT)
                        .build(),
                admin, null, null,
                List.of(tagCorporativo.getId(), tagNodeJs.getId(), tagOnboarding.getId())
        );

        // ========================
        // Categoría: Evento
        // ========================

        // 1. Aprobado - Martín Roldán
        testimonialService.save(
                Testimonial.builder()
                        .testimonial("El bootcamp intensivo que organizaron en enero fue un antes y un después para nuestro equipo docente. " +
                                "En tres días integramos herramientas que llevábamos meses postergando. Volvería sin dudarlo.")
                        .witness("Martín Roldán")
                        .rating(7)
                        .category(catEvento)
                        .state(StateTestimonial.APPROVED)
                        .build(),
                admin, null, null,
                List.of(tagOnboarding.getId(), tagCorporativo.getId())
        );

        // 2. Rechazado → ARCHIVED - Daniel Pereira
        testimonialService.save(
                Testimonial.builder()
                        .testimonial("El webinar prometía mucho, pero fue solo una demo de ventas. Esperaba contenido técnico real.")
                        .witness("Daniel Pereira")
                        .rating(3)
                        .category(catEvento)
                        .state(StateTestimonial.ARCHIVED)
                        .build(),
                admin, null, null,
                List.of(tagWebinar.getId())
        );

        // ========================
        // Categoría: Cliente
        // ========================

        // 1. Borrador - Sofía Paredes
        testimonialService.save(
                Testimonial.builder()
                        .testimonial("Implementamos el módulo de onboarding para 1.200 empleados nuevos y el tiempo de adaptación bajó a la mitad. " +
                                "El equipo de soporte estuvo con nosotros en cada etapa. No es solo un software, es un socio.")
                        .witness("Sofía Paredes")
                        .rating(5)
                        .category(catCliente)
                        .state(StateTestimonial.DRAFT)
                        .build(),
                admin, null, null,
                List.of()
        );

        // 2. Despublicado → ARCHIVED - Andrea Molina
        testimonialService.save(
                Testimonial.builder()
                        .testimonial("Llevamos dos años usando la plataforma en 300 escuelas públicas rurales. " +
                                "La tasa de abandono escolar en esas instituciones bajó 18 puntos. Los datos hablan solos.")
                        .witness("Andrea Molina")
                        .category(catCliente)
                        .state(StateTestimonial.ARCHIVED)
                        .build(),
                admin, null, null,
                List.of(tagOnboarding.getId(), tagCorporativo.getId())
        );

        // 3. Rechazado → ARCHIVED - Natalia Fuentes
        testimonialService.save(
                Testimonial.builder()
                        .testimonial("Usamos el módulo de onboarding para incorporar a más de 800 vendedores nuevos en temporada alta. " +
                                "Lo recomendaría a cualquier empresa de retail.")
                        .witness("Natalia Fuentes")
                        .rating(6)
                        .category(catCliente)
                        .state(StateTestimonial.ARCHIVED)
                        .build(),
                admin, null, null,
                List.of(tagOnboarding.getId(), tagCorporativo.getId())
        );

        // ========================
        // Categoría: Industria
        // ========================

        // 1. Rechazado → ARCHIVED - Jorge Villanueva
        testimonialService.save(
                Testimonial.builder()
                        .testimonial("El sector edtech en Latinoamérica necesitaba una solución que entendiera nuestra realidad: " +
                                "bajo ancho de banda, dispositivos heterogéneos y docentes sin tiempo. Por fin existe.")
                        .witness("Jorge Villanueva")
                        .rating(8)
                        .category(catIndustria)
                        .state(StateTestimonial.ARCHIVED)
                        .build(),
                admin, null, null,
                List.of(tagMobileFirst.getId(), tagEdTechLatam.getId())
        );

        // 2. Rechazado → ARCHIVED - Patricia Guzmán
        testimonialService.save(
                Testimonial.builder()
                        .testimonial("Aprendí marketing digital de cero con los cursos y la verdad es que ya tengo mis primeros clientes. " +
                                "La industria del marketing digital está cambiando muy rápido y esta plataforma te mantiene al día con todo eso.")
                        .witness("Patricia Guzmán")
                        .rating(6)
                        .category(catIndustria)
                        .state(StateTestimonial.ARCHIVED)
                        .build(),
                admin, null, null,
                List.of(tagMarketingDigital.getId())
        );
    }
}