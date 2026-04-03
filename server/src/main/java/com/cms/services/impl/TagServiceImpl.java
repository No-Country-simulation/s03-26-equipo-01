package com.cms.services.impl;

import com.cms.exception.EntityNotFoundException;
import com.cms.exception.business.BusinessException;
import com.cms.exception.business.impl.DuplicateResourceException;
import com.cms.model.Tag;
import com.cms.persistence.sql.TagSQLDAO;
import com.cms.services.TagService;
import java.text.Normalizer;
import java.util.List;
import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagSQLDAO tagSQLDAO;

    @Override
    public Tag create(Tag tag) {
        String normalizedName = normalizeName(tag.getName());
        tag.setName(normalizedName);
        tag.setSlug(generateSlug(normalizedName));
        return save(tag);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tag> findAll() {
        return tagSQLDAO.findAllByIsActiveTrueOrderByNameAsc();
    }

    @Override
    @Transactional(readOnly = true)
    public Tag findById(Long id) {
        return tagSQLDAO.findByIdAndIsActiveTrue(id)
                .orElseThrow(() -> new EntityNotFoundException(Tag.class.getSimpleName(), id));
    }

    @Override
    public Tag update(Long id, Tag tagData) {
        Tag tagToUpdate = findById(id);
        String normalizedName = normalizeName(tagData.getName());
        String slug = generateSlug(normalizedName);

        tagToUpdate.updateTag(normalizedName, slug);

        return save(tagToUpdate);
    }

    @Override
    public void deleteById(Long id) {
        Tag tag = findById(id);
        tag.getTestimonials().clear();
        tag.setActive(false);
        tagSQLDAO.saveAndFlush(tag);
    }

    private Tag save(Tag tag) {
        try {
            return tagSQLDAO.saveAndFlush(tag);
        } catch (DataIntegrityViolationException exception) {
            throw new DuplicateResourceException("Ya existe un tag con ese nombre o slug");
        }
    }



    private String normalizeName(String name) {
        if (name == null || name.isBlank()) {
            throw new BusinessException("El nombre del tag es obligatorio");
        }

        return name.strip()
                .replaceAll("\\s+", " ")
                .toLowerCase(Locale.ROOT);
    }

    private String generateSlug(String name) {
        String slug = Normalizer.normalize(name, Normalizer.Form.NFD)
                .replaceAll("\\p{M}+", "")
                .replaceAll("[^a-z0-9]+", "-")
                .replaceAll("(^-+|-+$)", "");

        if (slug.isBlank()) {
            throw new BusinessException("El nombre del tag es invalido");
        }

        return slug;
    }
}
