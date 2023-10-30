package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.model.dto.AdminCategoryDTO;
import com.example.demo.model.dto.CategoryDTO;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.utility.CategoryUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryUtility categoryUtility;

    public ResponseEntity<List<CategoryDTO>> getAllActiveCategories() {
        List<Category> categories = categoryRepository.findAll();

        List<CategoryDTO> categoryDTOList = categories.stream()
                .map(c -> categoryUtility.createCategoryDTO(c))
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(categoryDTOList);
    }

    public ResponseEntity<Map<String, String>> deleteCategory(Long categoryId) {
        if (categoryId == null || categoryId <= 0) {
            throw new IllegalArgumentException("Il categoryId è errato");
        }
        categoryRepository.updateIsActiveToFalseById(categoryId);
        Map<String,String> responseMap = new HashMap<>();
        responseMap.put("message", "Categoria eliminata con successo");
        return ResponseEntity.status(HttpStatus.OK).body(responseMap);
    }

    public ResponseEntity<List<AdminCategoryDTO>> getAll() {

        List<Category> categories = categoryRepository.findAll();
        List<AdminCategoryDTO> adminCategoryDTOList = categoryUtility.createAdminCategoryDTOLIst(categories);
        return ResponseEntity.status(HttpStatus.OK).body(adminCategoryDTOList);
    }
}
