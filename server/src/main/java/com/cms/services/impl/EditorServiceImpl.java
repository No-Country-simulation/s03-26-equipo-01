package com.cms.services.impl;

import com.cms.exception.EntityNotFoundException;
import com.cms.model.testimonial.Tag;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.enums.StateTestimonial;
import com.cms.model.user.impl.Editor;
import com.cms.persistence.sql.EditorSQLDAO;
import com.cms.services.EditorService;
import com.cms.services.TagService;
import com.cms.services.TestimonialService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EditorServiceImpl implements EditorService {

    private final TestimonialService testimonialService;

    private final TagService tagService;

    private final EditorSQLDAO editorSQLDAO;

    public EditorServiceImpl(TestimonialService testimonialService, TagService tagService, EditorSQLDAO editorSQLDAO) {
        this.testimonialService = testimonialService;
        this.tagService = tagService;
        this.editorSQLDAO = editorSQLDAO;
    }

    @Override
    public void asocTestimonial(Long idTestimonial, Long idEditor) {
        Editor editor = findById(idEditor);
        Testimonial testimonial = testimonialService.findTestimonialById(idTestimonial);

        editor.addDrafts(testimonial);

        testimonial.setEditor(editor);

        testimonialService.update(testimonial);

        editorSQLDAO.save(editor);
    }

    private Editor findById(Long idEditor) {
        return editorSQLDAO.findById(idEditor).orElseThrow(() -> new EntityNotFoundException(Editor.class.getName(), idEditor));
    }

    @Override
    public Testimonial advanceByEditor(Long idTestimonial, Long idEditor) {
        Editor editor = findById(idEditor);
        Testimonial testimonial = testimonialService.findTestimonialById(idTestimonial);

        editor.validateAdvance(testimonial);

        testimonial = testimonialService.advanceByEditor(idTestimonial);

        editor.removeDraft(testimonial);

        editorSQLDAO.save(editor);

        return testimonial;
    }

    @Override
    public Page<Testimonial> getTestimonialsToBank(Long idEditor, int page, int size) {
        Editor editor = findById(idEditor);

        return testimonialService.findAllTestimonial(page, size, editor.getCreatedBy(), StateTestimonial.DRAFT);
    }

    @Override
    public Testimonial findTestimonialByIdAndEditor(Long id, Long editorId) {
        Editor editor = findById(editorId);

        return testimonialService.findByIdAndEditor(id, editor);
    }

    @Override
    public List<Tag> findTagsByNameForEditor(String name, Long editorId, Long testimonialId) {
        Editor editor = findById(editorId);

        return testimonialService.getTagsIdUsedInTestimonialAscoEditor(editor, testimonialId, name);
    }


}
