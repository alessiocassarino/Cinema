package com.example.demo.utility;

import com.example.demo.model.Category;
import com.example.demo.model.dto.CategoryDTO;
import org.springframework.stereotype.Component;

@Component
public class CategoryUtility {

    public CategoryDTO createCategoryDTO(Category category) {
       return CategoryDTO.builder()
                .name(category.getName())
                .build();
    }
}
