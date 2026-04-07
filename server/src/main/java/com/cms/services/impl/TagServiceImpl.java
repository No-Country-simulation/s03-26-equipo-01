package com.cms.services.impl;

import com.cms.controller.dto.tag.TagUpdateRequestDTO;
import com.cms.exception.EntityNotFoundException;
import com.cms.model.testimonial.Tag;
import com.cms.model.user.impl.admin.Admin;
import com.cms.persistence.repository.TagRepository;
import com.cms.persistence.sql.AdminSQLDAO;
import com.cms.services.TagService;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TagServiceImpl implements TagService {

    private final AdminSQLDAO adminSQLDAO;

    private final TagRepository tagRepository;

    public TagServiceImpl(AdminSQLDAO adminSQLDAO, TagRepository tagRepository) {
        this.adminSQLDAO = adminSQLDAO;
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag create(Tag tag, Long idAdmin) {
        Admin admin = getAdmin(idAdmin);

        String normalizedName = normalizeName(tag.getName());

        tag.setName(normalizedName);

        tag.setCreator(admin);

        admin.agregarTag(tag);

        adminSQLDAO.save(admin);

        return save(tag);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tag> findAll() {
        return tagRepository.findAllByActiveTrueOrderByNameAsc();
    }

    @Override
    @Transactional(readOnly = true)
    public Tag findById(Long id) {
        return tagRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new EntityNotFoundException(Tag.class.getSimpleName(), id));
    }

    @Override
    public Tag update(Long id, TagUpdateRequestDTO updateTagDto) {
        Tag tagToUpdate = findById(id);

        String normalizedName = normalizeName(updateTagDto.name());

        tagToUpdate.updateTag(normalizedName);

        return save(tagToUpdate);
    }

    @Override
    public void deleteById(Long id) {
        Tag tag = findById(id);
        tag.clearTestimonials();
        tag.setActive(false);
        save(tag);
    }

    @Override
    public List<Tag> findTagsByName(String nameTag, Long idAdmin) {
        getAdmin(idAdmin);

        return tagRepository.findTagsByName(nameTag, idAdmin);
    }

    private Admin getAdmin(Long idAdmin) {
        return adminSQLDAO.findById(idAdmin).orElseThrow(() -> new EntityNotFoundException(Admin.class.getName(), idAdmin));
    }

    private Tag save(Tag tag) {
        return tagRepository.saveAndFlush(tag);
    }

    private String normalizeName(String name) {
        return name
                .trim()
                .toLowerCase()
                .replaceAll("\\s+", " ");
    }


}