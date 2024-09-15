package org.rhmodding.saffronrest.controllers;

import java.util.List;
import java.util.ArrayList;

import org.rhmodding.saffronrest.models.Category;
import org.rhmodding.saffronrest.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping(value = "/category")
    public List<Category> getCategories(
        @RequestParam(required=false) String name,
        @RequestParam(required=false) Integer category,
        @RequestParam(required=false) Integer game){
            return categoryRepository.findAll();
    }

    @GetMapping(value = "/category/{id}")
    public ResponseEntity<List<Category>> getCategory(
        @PathVariable Integer id ){
            var categoryTree = new ArrayList<Category>();
            Category category = categoryRepository.findById(id).orElse(null);
            categoryTree.add(category);
            Category parent = category.getParentCategory();
            while(parent != null){
                categoryTree.add(parent);
                parent = parent.getParentCategory();
            }
            return ResponseEntity.ok(categoryTree);
    }


}
