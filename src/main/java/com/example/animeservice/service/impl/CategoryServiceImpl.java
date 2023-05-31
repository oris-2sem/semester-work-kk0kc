package com.example.animeservice.service.impl;

import com.example.animeservice.model.Category;
import com.example.animeservice.repository.CategoryRepository;
import com.example.animeservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Qualifier("categoryRepository")
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public String saveCategory(Category category) {
        category.setActive(true);
        categoryRepository.saveAndFlush(category);
        return "Save Sucessfully";
    }

    @Override
    public List<Category> findAllCategories() {
        List<Category> categories = new ArrayList<>();
        List<Category> categoriesCopy = new ArrayList<>();
        categoryRepository.findAll().forEach(categories::add);
        categoriesCopy.addAll(categories);
        for (Category c : categories) {
            System.out.println(c);
            if (!c.isActive()) {
                categoriesCopy.remove(c);
            }
        }
        return categoriesCopy;

    }

    @Override
    public Category findCategoryById(Integer id) {
        Category category = categoryRepository.getOne(id);
        if (!category.isActive()) {
            category = null;
        }
        return category;
    }

    @Override
    public String deleteCategory(Integer id) {
        Category c = categoryRepository.getOne(id);
        c.setActive(false);
        categoryRepository.saveAndFlush(c);
        return "Deleted Successfully";
    }

    @Override
    public String updateCategory(Category category) {
        categoryRepository.saveAndFlush(category);
        return "Updated Successfully";
    }

}
