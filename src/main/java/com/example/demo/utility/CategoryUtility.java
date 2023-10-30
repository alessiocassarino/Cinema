package com.example.demo.utility;

import com.example.demo.model.Category;
import com.example.demo.model.dto.AdminCategoryDTO;
import com.example.demo.model.dto.CategoryDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryUtility {
    public CategoryDTO createCategoryDTO(Category category) {
       return CategoryDTO.builder()
                .name(category.getName())
                .build();
    }

    public List<AdminCategoryDTO> createAdminCategoryDTOLIst(List<Category> categories) {
        return categories.stream()
                .map(c -> createAdminCategoryDTO(c))
                .toList();
    }

    private AdminCategoryDTO createAdminCategoryDTO(Category c) {
        String status = getStatus(c.getIsActive());
        return AdminCategoryDTO.builder()
                .name(c.getName())
                .status(status)
                .build();
    }

    private String getStatus(Boolean isActive) {
        return isActive ? "attivo" : "inattivo";
    }
}
