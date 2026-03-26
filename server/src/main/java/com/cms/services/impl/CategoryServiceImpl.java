package com.cms.services.impl;

import com.cms.controller.dtos.CreateCategoryDto;
import com.cms.controller.dtos.UpdateCategoryDto;
import com.cms.exception.EntityNotFoundException;
import com.cms.exception.business.impl.DuplicateResourceException;
import com.cms.model.Category;
import com.cms.persistence.repository.CategoryRepository;
import com.cms.services.CategoryService;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category create(CreateCategoryDto createCategoryDto) {
        validateUniqueSlug(createCategoryDto.slug());

        Category category = Category.builder()
                .name(createCategoryDto.name())
                .slug(createCategoryDto.slug())
                .description(createCategoryDto.description())
                .build();

        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAllByDeletedFalse();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException(Category.class.getSimpleName(), id));
    }

    @Override
    public Category update(Long id, UpdateCategoryDto updateCategoryDto) {
        Category categoryToUpdate = findById(id);

        if (updateCategoryDto.slug() != null
                && categoryRepository.existsBySlugAndIdNot(updateCategoryDto.slug(), id)) {
            throw new DuplicateResourceException("Category slug already exists: " + updateCategoryDto.slug());
        }

        if (updateCategoryDto.name() != null) {
            categoryToUpdate.setName(updateCategoryDto.name());
        }
        if (updateCategoryDto.slug() != null) {
            categoryToUpdate.setSlug(updateCategoryDto.slug());
        }
        if (updateCategoryDto.description() != null) {
            categoryToUpdate.setDescription(updateCategoryDto.description());
        }

        return categoryRepository.save(categoryToUpdate);
    }

    @Override
    public void deleteById(Long id) {
        Category category = findById(id);
        category.setDeleted(true);
        categoryRepository.save(category);
    }

    private void validateUniqueSlug(String slug) {
        if (categoryRepository.existsBySlug(slug)) {
            throw new DuplicateResourceException("Category slug already exists: " + slug);
        }
    }
}
