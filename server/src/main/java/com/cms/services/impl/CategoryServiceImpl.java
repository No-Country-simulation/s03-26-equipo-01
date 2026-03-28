package com.cms.services.impl;

import com.cms.controller.dtos.UpdateCategoryDto;
import com.cms.exception.EntityNotFoundException;
import com.cms.model.Category;
import com.cms.persistence.repository.sql.CategorySQLDAO;
import com.cms.services.CategoryService;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategorySQLDAO categorySQLDAO;

    public CategoryServiceImpl(CategorySQLDAO categorySQLDAO) {
        this.categorySQLDAO = categorySQLDAO;
    }

    @Override
    public Category create(Category category) { // <-- Se limpió el método
        return categorySQLDAO.save(category);
    }

    @Override
    public List<Category> findAll() {
        return categorySQLDAO.findAllByDeletedFalse();
    }

    @Override
    public Category findById(Long id) {
        return categorySQLDAO.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new EntityNotFoundException(Category.class.getSimpleName(), id));
    }

    @Override
    public Category update(Long id, Category categoryData) {
        Category categoryToUpdate = findById(id);

        // Aquí usamos los datos que vienen de la entidad 'categoryData'
        if (categoryData.getName() != null) categoryToUpdate.setName(categoryData.getName());
        if (categoryData.getSlug() != null) categoryToUpdate.setSlug(categoryData.getSlug());
        if (categoryData.getDescription() != null) categoryToUpdate.setDescription(categoryData.getDescription());

        return categorySQLDAO.save(categoryToUpdate);
    }

    @Override
    public void deleteById(Long id) {
        Category category = findById(id);
        category.setDeleted(true);
        categorySQLDAO.save(category);
    }
}