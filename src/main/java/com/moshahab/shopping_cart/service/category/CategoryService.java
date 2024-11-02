package com.moshahab.shopping_cart.service.category;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.moshahab.shopping_cart.exceptions.AlreadyExistsException;
import com.moshahab.shopping_cart.exceptions.ResourceNotFoundException;
import com.moshahab.shopping_cart.model.Category;
import com.moshahab.shopping_cart.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    public final CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category Not Found!"));
    }

    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) {
        return Optional.of(category).filter(c -> !categoryRepository
                .existsByName(c.getName())).map(categoryRepository::save)
                .orElseThrow(() -> new AlreadyExistsException(category.getName() + "Already Exists"));
    }

    @Override
    public Category updateCategory(Category category, Long id) {
        return Optional.ofNullable(getCategoryById(id)).map(oldCategory -> {
            oldCategory.setName(category.getName());
            return categoryRepository.save(oldCategory);
        }).orElseThrow(() -> new ResourceNotFoundException("Category Not Found!"));
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.findById(id).ifPresentOrElse(categoryRepository::delete, () -> {
            throw new ResourceNotFoundException("Category Not Found!");
        });
    }
}
