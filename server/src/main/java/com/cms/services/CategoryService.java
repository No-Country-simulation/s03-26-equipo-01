package com.cms.services;

import com.cms.controller.dtos.CreateCategoryDto;
import com.cms.controller.dtos.UpdateCategoryDto;
import com.cms.model.Category;
import java.util.List;

public interface CategoryService {
    Category create(CreateCategoryDto createCategoryDto);

    List<Category> findAll();

    Category findById(Long id);

    Category update(Long id, UpdateCategoryDto updateCategoryDto);

    void deleteById(Long id);
}
