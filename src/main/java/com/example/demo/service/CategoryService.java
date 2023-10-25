package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.model.dto.CategoryDTO;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.utility.CategoryUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryUtility categoryUtility;

    public ResponseEntity<List<CategoryDTO>> getAll() {
        List<Category> categories = categoryRepository.findAll();

        List<CategoryDTO> categoryDTOList =  categories.stream()
                .map(c -> categoryUtility.createCategoryDTO(c))
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(categoryDTOList);
    }
}
