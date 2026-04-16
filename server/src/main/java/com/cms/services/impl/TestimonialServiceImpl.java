package com.cms.services.impl;

import com.cms.exception.EntityNotFoundException;
import com.cms.model.testimonial.Category;
import com.cms.model.testimonial.Media;
import com.cms.model.testimonial.Tag;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.enums.StateTestimonial;
import com.cms.model.user.impl.Editor;
import com.cms.model.user.impl.admin.Admin;
import com.cms.persistence.repository.TagRepository;
import com.cms.persistence.repository.TestimonialRepository;
import com.cms.persistence.sql.AdminSQLDAO;
import com.cms.persistence.sql.TagSQLDAO;
import com.cms.services.*;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class TestimonialServiceImpl implements TestimonialService {

    private final TestimonialRepository testimonialRepository;

    private final MediaService mediaService;

    private final AdminSQLDAO adminSQLDAO;

    private final TagSQLDAO  tagDAO;

    private final TagRepository tagRepository;
    private final CategoryService categoryService;

    public TestimonialServiceImpl(TestimonialRepository testimonialRepository,
                                  MediaService mediaService,
                                  AdminSQLDAO adminSQLDAO, TagSQLDAO tagDAO, TagRepository tagRepository, CategoryService categoryService) {

        this.testimonialRepository = testimonialRepository;

        this.mediaService = mediaService;

        this.adminSQLDAO = adminSQLDAO;

        this.tagDAO = tagDAO;
        this.tagRepository = tagRepository;
        this.categoryService = categoryService;
    }

    @Override
    public Testimonial save(Testimonial model, Admin admin, MultipartFile image, String youtubeUrl, List<Long> idTags) {
        Media media = mediaService.save(image, youtubeUrl);

        model.setAdmin(admin);
        model.setMedia(media);
        if(idTags != null)        model.agregarTags(tagDAO.findAllById(idTags));

        Testimonial testimonial = testimonialRepository.save(model);

        admin.addTestimonial(testimonial);

        adminSQLDAO.save(admin);

        return testimonial;
    }

    @Override
    public Testimonial findTestimonialById(Long id) {
        return testimonialRepository.findById(id);
    }

    @Override
    public List<Testimonial> findTestimonialByAdmin(Long idAdmin) {
        if (!adminSQLDAO.existsById(idAdmin)) {
            throw new EntityNotFoundException(Admin.class.getName(), idAdmin);
        }
        return testimonialRepository.findByAdminId(idAdmin);
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
    public Testimonial update(Testimonial recovered) {
        return testimonialRepository.update(recovered);
    }

    @Override
    public Page<Testimonial> findAllTestimonial(int pageNumber, int size, Admin admin, StateTestimonial state) {
        return testimonialRepository.findAllTestimonial(PageRequest.of(pageNumber, size), admin, state);
    }

    @Override
    public Testimonial archiveTestimonial(Long idTestimonial, Admin admin) {
        Testimonial testimonial = testimonialRepository.findTestimonialByIdAndAdmin(idTestimonial, admin);

        testimonial.archived();

        return testimonialRepository.update(testimonial);
    }

    @Override
    public Page<Testimonial> getTestimonialsByEditor(Editor editor, int page, int size) {
        return testimonialRepository.getDraftsByEditor(editor, PageRequest.of(page, size));
    }

    @Override
    public List<Testimonial> findAllTestimonialPublished(Admin admin, StateTestimonial stateTestimonial) {
        return testimonialRepository.findAllTestimonialPublished(admin, stateTestimonial);
    }

    @Override
    public Testimonial findByIdAndEditor(Long id, Editor editor) {
        return testimonialRepository.findByIdAndEditor(id, editor);
    }

    @Override
    public List<Tag> getTagsIdUsedInTestimonialAscoEditor(Editor editor, Long testimonialId, String name) {
        List<Long> ids = testimonialRepository.getTagsIdUsedInTestimonialAscoEditor(editor, testimonialId);
        return tagRepository.findTagsByNameExcludeIds(ids, editor.getCreatedBy().getId(), name);
    }
}
