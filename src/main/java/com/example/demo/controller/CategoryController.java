package com.example.demo.controller;


import com.example.demo.model.dto.CategoryDTO;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @GetMapping("categories")
    public ResponseEntity<List<CategoryDTO>> getAll() {
        return categoryService.getAll();
    }

    @GetMapping("deleteCategory/{categoryId}")
    public ResponseEntity<Map<String, String>> deleteCategory(@PathVariable Long categoryId) {
        return categoryService.deleteCategory(categoryId);
    }
}
