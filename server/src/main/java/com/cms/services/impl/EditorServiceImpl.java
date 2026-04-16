package com.cms.services.impl;

import com.cms.controller.dto.testimonial.TestimonialUpdateDTO;
import com.cms.exception.EntityNotFoundException;
import com.cms.model.testimonial.Category;
import com.cms.model.testimonial.Tag;
import com.cms.model.testimonial.Testimonial;
import com.cms.model.testimonial.enums.StateTestimonial;
import com.cms.model.user.impl.Editor;
import com.cms.persistence.repository.MediaRepository;
import com.cms.persistence.sql.EditorSQLDAO;
import com.cms.services.CategoryService;
import com.cms.services.EditorService;
import com.cms.services.TestimonialService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EditorServiceImpl implements EditorService {

    private final TestimonialService testimonialService;

    private final CategoryService categoryService;

    private final EditorSQLDAO editorSQLDAO;
    
    private final MediaRepository mediaRepository;

    public EditorServiceImpl(TestimonialService testimonialService, CategoryService categoryService, EditorSQLDAO editorSQLDAO, MediaRepository mediaRepository) {
        this.testimonialService = testimonialService;
        this.categoryService = categoryService;
        this.editorSQLDAO = editorSQLDAO;
        this.mediaRepository = mediaRepository;
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

    @Override
    public Testimonial updateTestimonial(TestimonialUpdateDTO model, Long editorId, Long categoryId) {
        Category category = categoryService.findById(categoryId);
        Editor editor = findById(editorId);

        editor.validateUpdateTestimonial(editorSQLDAO.containTestimonial(model.id(), editor));

        Testimonial testimonial = testimonialService.findTestimonialById(model.id());

        String previousVideoId = testimonial.getMedia() != null ? testimonial.getMedia().getVideoId() : null;
        String previousImagePublicId = testimonial.getMedia() != null ? testimonial.getMedia().getPublicId() : null;

        model.updateTestimonial(testimonial);

        testimonial.setCategory(category);

        Testimonial updated = testimonialService.update(testimonial);

        if (!model.displayOptions().showVideo() && previousVideoId != null) {
            mediaRepository.clearVideoField(previousVideoId);
        }
        
        if (!model.displayOptions().showImage() && previousImagePublicId != null) {
            mediaRepository.clearImageFields(previousImagePublicId);
        }

        return updated;
    }

    @Override
    public Page<Testimonial> getDrafts(Long idEditor, int page, int size) {
        Editor editor = findById(idEditor);
        return testimonialService.getTestimonialsByEditor(editor, page, size);
    }


}
