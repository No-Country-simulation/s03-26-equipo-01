package com.cms.services;

import com.cms.controller.dtos.UpdateCategoryDto;
import com.cms.model.Category;
import java.util.List;

public interface CategoryService {
    Category create(Category category); // <-- Ahora recibe la Entidad

    List<Category> findAll();

    Category findById(Long id);

    Category update(Long id, Category categoryData); // Recibe la entidad con los cambios

    void deleteById(Long id);
}