package com.cms.configuration.data.impl;

import com.cms.configuration.data.DataSeeder;
import com.cms.model.embeds.Embed;
import com.cms.model.testimonial.Category;
import com.cms.model.testimonial.Media;
import com.cms.model.testimonial.Tag;
import com.cms.model.user.impl.admin.Admin;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.enums.StateTestimonial;
import com.cms.model.user.impl.Editor;
import com.cms.persistence.repository.MediaRepository;
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
    private final YoutubeService youtubeService;
    private final MediaRepository mediaRepository;

    private Admin admin;

    public DataSeederImpl(UserService userService, AdminService adminService, EmbedService embedService,
                          TestimonialService testimonialService, CategoryService categoryService, TagService tagService,
                          YoutubeService youtubeService, MediaRepository mediaRepository) {
        this.userService = userService;
        this.adminService = adminService;
        this.embedService = embedService;
        this.testimonialService = testimonialService;
        this.categoryService = categoryService;
        this.tagService = tagService;
        this.youtubeService = youtubeService;
        this.mediaRepository = mediaRepository;
    }

    @Override
    public void run(String... args) {
        admin = adminService.save(
                Admin.builder()
                        .email("tomasmendoza@gmail.com")
                        .password("123")
                        .firstName("Tomas")
                        .lastName("Mendoza")
                        .build()
        );

        embedService.registerEmbed(admin.getId(), new Embed());
        Embed embed = embedService.registerEmbed(admin.getId(), new Embed());

        seedEditors();
        seedTestimonials(embed.getId());
    }

    private void seedEditors() {
        List<Editor> editors = List.of(
                Editor.builder().email("valeria.herrera@gmail.com").password("123").firstName("Valeria").lastName("Herrera").createdBy(admin).build(),
                Editor.builder().email("miguel.delgado@gmail.com").password("123").firstName("Miguel").lastName("Delgado").createdBy(admin).build(),
                Editor.builder().email("fernanda.rios@gmail.com").password("123").firstName("Fernanda").lastName("Ríos").createdBy(admin).build(),
                Editor.builder().email("sebastian.medina@gmail.com").password("123").firstName("Sebastián").lastName("Medina").createdBy(admin).build(),
                Editor.builder().email("camila.vargas@gmail.com").password("123").firstName("Camila").lastName("Vargas").createdBy(admin).build(),
                Editor.builder().email("rodrigo.ibarra@gmail.com").password("123").firstName("Rodrigo").lastName("Ibarra").createdBy(admin).build(),
                Editor.builder().email("lucia.escobar@gmail.com").password("123").firstName("Lucía").lastName("Escobar").createdBy(admin).build(),
                Editor.builder().email("esteban.campos@gmail.com").password("123").firstName("Esteban").lastName("Campos").createdBy(admin).build(),
                Editor.builder().email("mariana.salazar@gmail.com").password("123").firstName("Mariana").lastName("Salazar").createdBy(admin).build(),
                Editor.builder().email("pablo.zuñiga@gmail.com").password("123").firstName("Pablo").lastName("Zúñiga").createdBy(admin).build(),
                Editor.builder().email("alejandra.mora@gmail.com").password("123").firstName("Alejandra").lastName("Mora").createdBy(admin).build()
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

        // --- Tags - Complete list ---
        Tag tagAumentoSalario = tagService.create(Tag.builder().name("Aumento salario").build(), admin.getId());
        Tag tagAprendizajeAutodidacta = tagService.create(Tag.builder().name("Aprendizaje autodidacta").build(), admin.getId());
        Tag tagAscensoLaboral = tagService.create(Tag.builder().name("Ascenso laboral").build(), admin.getId());
        Tag tagAprendiendoProgramar = tagService.create(Tag.builder().name("Aprendiendo a programar").build(), admin.getId());
        Tag tagBackend = tagService.create(Tag.builder().name("Backend").build(), admin.getId());
        Tag tagBuenPrecioCalidad = tagService.create(Tag.builder().name("Buen precio-calidad").build(), admin.getId());
        Tag tagCertificacion = tagService.create(Tag.builder().name("Certificación").build(), admin.getId());
        Tag tagCambioIndustria = tagService.create(Tag.builder().name("Cambio de industria").build(), admin.getId());
        Tag tagCambioCarrera = tagService.create(Tag.builder().name("Cambio de carrera").build(), admin.getId());
        Tag tagComunidad = tagService.create(Tag.builder().name("Comunidad").build(), admin.getId());
        Tag tagContenidoActualizado = tagService.create(Tag.builder().name("Contenido actualizado").build(), admin.getId());
        Tag tagCiberseguridad = tagService.create(Tag.builder().name("Ciberseguridad").build(), admin.getId());
        Tag tagCorporativo = tagService.create(Tag.builder().name("Corporativo").build(), admin.getId());
        Tag tagDiseñoUxUi = tagService.create(Tag.builder().name("Diseño UX/UI").build(), admin.getId());
        Tag tagDataIA = tagService.create(Tag.builder().name("Data & IA").build(), admin.getId());
        Tag tagDevOps = tagService.create(Tag.builder().name("DevOps").build(), admin.getId());
        Tag tagDataEngineering = tagService.create(Tag.builder().name("Data Engineering").build(), admin.getId());
        Tag tagDevJunior = tagService.create(Tag.builder().name("Dev junior").build(), admin.getId());
        Tag tagDevSenior = tagService.create(Tag.builder().name("Dev senior").build(), admin.getId());
        Tag tagDashboardDocente = tagService.create(Tag.builder().name("Dashboard docente").build(), admin.getId());
        Tag tagEstudiante = tagService.create(Tag.builder().name("Estudiante").build(), admin.getId());
        Tag tagEstudianteUniversitario = tagService.create(Tag.builder().name("Estudiante universitario").build(), admin.getId());
        Tag tagEstudianteTrabajador = tagService.create(Tag.builder().name("Estudiante y trabajador").build(), admin.getId());
        Tag tagEconomico = tagService.create(Tag.builder().name("Económico").build(), admin.getId());
        Tag tagEdTechLatam = tagService.create(Tag.builder().name("EdTech LATAM").build(), admin.getId());
        Tag tagFinanzas = tagService.create(Tag.builder().name("Finanzas").build(), admin.getId());
        Tag tagFlexibilidadHoraria = tagService.create(Tag.builder().name("Flexibilidad horaria").build(), admin.getId());
        Tag tagFullStack = tagService.create(Tag.builder().name("Full Stack").build(), admin.getId());
        Tag tagFrontend = tagService.create(Tag.builder().name("Frontend").build(), admin.getId());
        Tag tagFreelance = tagService.create(Tag.builder().name("Freelance").build(), admin.getId());
        Tag tagGestionLiderazgo = tagService.create(Tag.builder().name("Gestión y liderazgo").build(), admin.getId());
        Tag tagLearningAnalytics = tagService.create(Tag.builder().name("Learning Analytics").build(), admin.getId());
        Tag tagMetodologiaEnseñanza = tagService.create(Tag.builder().name("Metodología de enseñanza").build(), admin.getId());
        Tag tagMarketingDigital = tagService.create(Tag.builder().name("Marketing digital").build(), admin.getId());
        Tag tagMobileIos = tagService.create(Tag.builder().name("Mobile iOS").build(), admin.getId());
        Tag tagMobileAndroid = tagService.create(Tag.builder().name("Mobile Android").build(), admin.getId());
        Tag tagMentoria = tagService.create(Tag.builder().name("Mentoría").build(), admin.getId());
        Tag tagMobileFirst = tagService.create(Tag.builder().name("Mobile first").build(), admin.getId());
        Tag tagNodeJs = tagService.create(Tag.builder().name("Node js").build(), admin.getId());
        Tag tagOnboarding = tagService.create(Tag.builder().name("Onboarding").build(), admin.getId());
        Tag tagProgramacion = tagService.create(Tag.builder().name("Programación").build(), admin.getId());
        Tag tagProfesionalTransicion = tagService.create(Tag.builder().name("Profesional en transición").build(), admin.getId());
        Tag tagProyectosReales = tagService.create(Tag.builder().name("Proyectos reales").build(), admin.getId());
        Tag tagRestApis = tagService.create(Tag.builder().name("REST APIs").build(), admin.getId());
        Tag tagSinExperiencia = tagService.create(Tag.builder().name("Sin experiencia").build(), admin.getId());
        Tag tagTutoria = tagService.create(Tag.builder().name("Tutoría").build(), admin.getId());
        Tag tagTesting = tagService.create(Tag.builder().name("Testing").build(), admin.getId());
        Tag tagWebinar = tagService.create(Tag.builder().name("Webinar").build(), admin.getId());
        Tag tagWorkshop = tagService.create(Tag.builder().name("Workshop").build(), admin.getId());

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
                        .media(createMedia("https://unsplash.com/es/fotos/mujer-presentando-datos-en-una-computadora-portatil-y-pantalla-MXFE74HZjnQ",
                                "https://youtu.be/xzxI-7mWVCI?si=u0zZeokdeAlbKXgJ&t=17"))
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
                        .media(createMedia(null, "https://youtu.be/xzxI-7mWVCI?si=arcmg7-nBmFDIy26&t=95"))
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
                        .media(createMedia(null, "https://youtu.be/kdMG40wUCm4?si=0Z_fy07VwXrsjpN5&t=12"))
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
                        .media(createMedia(null, "https://youtu.be/LBNDfxjEYlA?si=6oe032ygm0lf0vS_"))
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
                        .media(createMedia("https://www.pexels.com/es-es/foto/hombre-persona-trabajando-teclear-6147356/", null))
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
                        .media(createMedia(null, null))
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
                        .media(createMedia("https://www.pexels.com/es-es/foto/persona-mujer-ordenador-portatil-oficina-8837188/", null))
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
                        .rating(3)
                        .category(catCliente)
                        .state(StateTestimonial.ARCHIVED)
                        .media(createMedia("https://unsplash.com/es/fotos/una-mujer-con-gafas-mirando-un-portatil-pj-45SrF4Gs", null))
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
                        .media(createMedia(null, null))
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
                        .media(createMedia("https://unsplash.com/es/fotos/dos-hombres-sentados-frente-a-una-computadora-portatil-Hp4RPL_Z6wE", null))
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
                        .media(createMedia("https://unsplash.com/es/fotos/mujer-joven-con-auriculares-estudia-en-el-escritorio-xufhBqFUv60", null))
                        .build(),
                admin, null, null,
                List.of(tagMarketingDigital.getId())
        );
    }

    private Media createMedia(String imageUrl, String youtubeUrl) {
        // Las imágenes requieren MultipartFile y Cloudinary. Las URLs directas se ignoran.
        // Solo procesamos videos de YouTube
        Media videoMedia = null;
        if (youtubeUrl != null && !youtubeUrl.isBlank()) {
            videoMedia = youtubeService.fromUrl(youtubeUrl);
        }

        return mediaRepository.save(null, videoMedia);
    }
}